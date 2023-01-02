package dev.migwel.icyreader.parser;

import dev.migwel.icyreader.SongInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class StreamTitleArtistFirstParser implements IcyStreamTitleParser {

    private static final Logger log = LogManager.getLogger(StreamTitleArtistFirstParser.class);

    @Override
    @Nonnull
    public SongInfo parse(String streamTitle) {
        String[] songInfoSplit = streamTitle.split(" - ");
        if (songInfoSplit.length < 2) {
            log.info("Wrong size ("+ songInfoSplit.length +") for StreamTitle metadata: "+ streamTitle);
            return new SongInfo(streamTitle, null, null);
        }
        return new SongInfo(streamTitle, songInfoSplit[0], songInfoSplit[1]);
    }
}
