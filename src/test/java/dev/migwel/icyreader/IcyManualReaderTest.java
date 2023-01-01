package dev.migwel.icyreader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IcyManualReaderTest {

    private final IcyReader reader = new IcyReader();

    @Test
    void readRadio2Stream() {
        SongInfo songInfo = reader.currentlyPlaying("https://icecast.omroep.nl/radio2-bb-mp3");
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }
}
