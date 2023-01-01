package dev.migwel.icyreader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IcyManualReaderTest {

    private final IcyReader reader = new IcyReader();

    @Test
    void readNpoRadio1Stream() {
        SongInfo songInfo = reader.currentlyPlaying(Sources.NPORADIO1);
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readNpoRadio2Stream() {
        SongInfo songInfo = reader.currentlyPlaying(Sources.NPORADIO2);
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readNpoRadio5Stream() {
        SongInfo songInfo = reader.currentlyPlaying(Sources.NPORADIO5);
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readKEXPStream() {
        SongInfo songInfo = reader.currentlyPlaying(Sources.KEXP);
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readRadio538Stream() {
        SongInfo songInfo = reader.currentlyPlaying(Sources.RADIO538);
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readMaximumStream() {
        SongInfo songInfo = reader.currentlyPlaying(Sources.MAXIMUMFM);
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }
}
