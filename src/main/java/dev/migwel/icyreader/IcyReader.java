package dev.migwel.icyreader;

import dev.migwel.icyreader.parser.StreamTitleArtistFirstParser;
import dev.migwel.icyreader.parser.IcyStreamTitleParser;
import dev.migwel.icyreader.retriever.HttpIcyStreamRetriever;
import dev.migwel.icyreader.retriever.IcyStream;
import dev.migwel.icyreader.retriever.IcyStreamRetriever;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
public class IcyReader {

    private static final Logger log = LogManager.getLogger(IcyReader.class);
    public static final int WAIT_PRE_ROLL = 10_000;
    private final IcyStreamRetriever streamRetriever;
    private final IcyStreamTitleParser streamTitleParser;
    private final String streamUrl;

    public IcyReader(IcyStreamRetriever streamRetriever, IcyStreamTitleParser streamTitleParser, String streamUrl) {
        this.streamRetriever = streamRetriever;
        this.streamTitleParser = streamTitleParser;
        this.streamUrl = streamUrl;
    }

    /**
     * This method can be used to fetch what song is currently playing on a Shoutcast/Icecast stream
     * @return a SongInfo object containing the artist and song currently playing on the streamUrl
     *         If this could not be retrieved, returns null
     */
    @CheckForNull
    public SongInfo currentlyPlaying() {
        List<Metadata> metadata;
        try (IcyStream icyStream = streamRetriever.retrieve(streamUrl)) {
            if (icyStream == null) {
                log.info("Stream could not be retrieved");
                return null;
            }

            metadata = getMetaData(icyStream);
        } catch (IOException e) {
            log.warn("Could not close input stream", e);
            return null;
        }
        return getSongInfo(metadata);
    }

    private List<Metadata> getMetaData(IcyStream icyStream) {
        int metadataOffset = getMetadataOffset(icyStream.getIcyMetaInt());
        List<Metadata> metadata;
        long startMs = System.currentTimeMillis();
        do {
            try {
                metadata = extractMetadata(icyStream.getStream(), metadataOffset);
            } catch (IOException e) {
                log.warn("Could not get metadata", e);
                return Collections.emptyList();
            }
        }
        while ((metadata.isEmpty() || containsPreroll(metadata)) && System.currentTimeMillis() - startMs <= WAIT_PRE_ROLL);

        return metadata;
    }

    private boolean containsPreroll(List<Metadata> metadata) {
        return metadata.stream().anyMatch(e -> "insertionType".equals(e.getKey()) && "preroll".equals(e.getValue()));
    }

    private int getMetadataOffset(String icyMetaInt) {
        if ("".equals(icyMetaInt)) {
            return 0;
        }
        icyMetaInt = icyMetaInt.replace("[", "");
        icyMetaInt = icyMetaInt.replace("]", "");

        return Integer.parseInt(icyMetaInt);
    }

    @CheckForNull
    private SongInfo getSongInfo(List<Metadata> metadata) {
        Metadata streamTitle = metadata.stream().filter(e -> "StreamTitle".equals(e.getKey())).findFirst().orElse(null);
        if (streamTitle == null) {
            return null;
        }
        return streamTitleParser.parse(streamTitle.getValue());
    }

    //Returns an empty list when nothing has changed
    @Nonnull
    private List<Metadata> extractMetadata(InputStream stream, int metadataOffset) throws IOException {
        stream.skipNBytes(metadataOffset);
        int metaDataLength = stream.read() * 16;
        if (metaDataLength == 0) {
            //This means nothing has changed, ignore
            return Collections.emptyList();
        }
        String metadataStr = getMetadataStr(stream, metaDataLength);
        return Arrays.stream(metadataStr.split(";"))
                .map(e -> e.split("=", 2))
                .filter(e -> e.length == 2)
                .map(e -> new Metadata(e[0], e[1].substring(1, e[1].length()-1)))
                .collect(Collectors.toList());
    }

    @Nonnull
    private String getMetadataStr(InputStream stream, int metaDataLength) throws IOException {
        byte[] b = new byte[metaDataLength];
        if (stream.read(b, 0, metaDataLength) != metaDataLength) {
            throw new IOException("Could not read "+ metaDataLength +" bytes from stream");
        }
        return new String(b, StandardCharsets.UTF_8);
    }

    @ParametersAreNonnullByDefault
    public static class IcyReaderBuilder {

        private IcyStreamRetriever streamRetriever;
        private IcyStreamTitleParser icyStreamTitleParser;
        private final String streamUrl;

        public IcyReaderBuilder(String streamUrl) {
            this.streamUrl = streamUrl;
        }

        public IcyReaderBuilder(Sources sources) {
            this.streamUrl = sources.getUrl();
            this.icyStreamTitleParser = sources.getStreamTitleParser();
        }

        public IcyReaderBuilder withRetriever(IcyStreamRetriever streamRetriever) {
            this.streamRetriever = streamRetriever;
            return this;
        }

        public IcyReaderBuilder withIcyStreamTitleParser(IcyStreamTitleParser icyStreamTitleParser) {
            this.icyStreamTitleParser = icyStreamTitleParser;
            return this;
        }

        public IcyReader build() {
            if (streamRetriever == null) {
                streamRetriever = new HttpIcyStreamRetriever();
            }
            if (icyStreamTitleParser == null) {
                icyStreamTitleParser = new StreamTitleArtistFirstParser();
            }
            return new IcyReader(streamRetriever, icyStreamTitleParser, streamUrl);
        }

    }
}
