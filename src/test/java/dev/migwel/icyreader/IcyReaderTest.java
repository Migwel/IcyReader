package dev.migwel.icyreader;

import dev.migwel.icyreader.retriever.TestIcyStreamRetriever;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 We have different meta-int per test because the MP3 files used come from different source with different values.
 Ideally, we'd build these files ourselves and use the same meta-int everywhere but that's requiring too much
 work that I'm not willing to put in at this moment
 */
public class IcyReaderTest {

    @Test
    void readValidStream() {
        IcyReader reader = new IcyReader(new TestIcyStreamRetriever(16000));
        SongInfo songInfo = reader.currentlyPlaying("valid");
        assertNotNull(songInfo);
        assertEquals("TITIYO", songInfo.artist());
        assertEquals("COME ALONG", songInfo.title());
    }

    @Test
    void readValidStreamWithPreRoll() {
        IcyReader reader = new IcyReader(new TestIcyStreamRetriever(4096));
        SongInfo songInfo = reader.currentlyPlaying("valid_with_preroll");
        assertNotNull(songInfo);
        assertEquals("Aretha Franklin", songInfo.artist());
        assertEquals("A Brand New Me", songInfo.title());
    }

    @Test
    void readInvalidStream() {
        IcyReader reader = new IcyReader(new TestIcyStreamRetriever(16000));
        SongInfo songInfo = reader.currentlyPlaying("invalid");
        assertNull(songInfo);
    }
}
