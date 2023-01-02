package dev.migwel.icyreader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IcyManualReaderTest {

    @Test
    void readNpoRadio1Stream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.NPORADIO1).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readNpoRadio2Stream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.NPORADIO2).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readNpoRadio5Stream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.NPORADIO5).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readKEXPStream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.KEXP).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readRadio538Stream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.RADIO538).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readQMusicStream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.QMUSIC).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readSkyRadioStream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.SKYRADIO).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void read100procentNLStream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.HONDERDPROCENTNL).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }

    @Test
    void readMaximumStream() {
        IcyReader reader = new IcyReader.IcyReaderBuilder().withSources(Sources.MAXIMUMFM).build();
        SongInfo songInfo = reader.currentlyPlaying();
        assertNotNull(songInfo);
        System.out.println(songInfo);
    }
}
