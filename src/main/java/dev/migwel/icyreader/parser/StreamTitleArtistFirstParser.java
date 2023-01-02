package dev.migwel.icyreader.parser;

import dev.migwel.icyreader.SongInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamTitleArtistFirstParser implements IcyStreamTitleParser {

    private static final Logger log = LogManager.getLogger(StreamTitleArtistFirstParser.class);

    @Override
    public SongInfo parse(String streamTitle) {
        String[] songInfoSplit = streamTitle.split(" - ");
        if (songInfoSplit.length < 2) {
            log.info("Wrong size ("+ songInfoSplit.length +") for StreamTitle metadata: "+ streamTitle);
            return null;
        }
        return new SongInfo(songInfoSplit[0], songInfoSplit[1]);
    }
}
