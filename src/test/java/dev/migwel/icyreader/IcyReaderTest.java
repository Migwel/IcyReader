package dev.migwel.icyreader;

import dev.migwel.icyreader.retriever.TestIcyStreamRetriever;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IcyReaderTest {

    private final IcyReader reader = new IcyReader(new TestIcyStreamRetriever());

    @Test
    void readValidStream() {
        SongInfo songInfo = reader.currentlyPlaying("valid");
        assertNotNull(songInfo);
        assertEquals("TITIYO", songInfo.artist());
        assertEquals("COME ALONG", songInfo.title());
    }

    @Test
    void readInvalidStream() {
        SongInfo songInfo = reader.currentlyPlaying("invalid");
        assertNull(songInfo);
    }
}
