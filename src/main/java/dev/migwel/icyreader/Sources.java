package dev.migwel.icyreader;

import dev.migwel.icyreader.parser.IcyStreamTitleParser;
import dev.migwel.icyreader.parser.StreamTitleArtistFirstParser;
import dev.migwel.icyreader.parser.StreamTitleArtistSecondParser;

public enum Sources {

    //BE
    MAXIMUMFM("https://n01a-eu.rcs.revma.com/vnmbzemrmm0uv"),

    //NL
    HONDERDPROCENTNL("http://stream.100p.nl/100pctnl.mp3"),
    NPORADIO1("https://icecast.omroep.nl/radio1-bb-mp3"),
    NPORADIO2("https://icecast.omroep.nl/radio2-bb-mp3"),
    NPORADIO5("https://icecast.omroep.nl/radio5-bb-mp3"),
    RADIO538("https://25703.live.streamtheworld.com/RADIO538.mp3"),
    QMUSIC("https://icecast-qmusicnl-cdp.triple-it.nl/Qmusic_nl_live.mp3", new StreamTitleArtistSecondParser()),
    SKYRADIO("http://playerservices.streamtheworld.com/api/livestream-redirect/SKYRADIO.mp3"),

    //US
    KEXP("https://kexp-mp3-128.streamguys1.com/kexp128.mp3");

    private final String url;
    private final IcyStreamTitleParser streamTitleParser;

    Sources(String url) {
        this(url, new StreamTitleArtistFirstParser());
    }

    Sources(String url, IcyStreamTitleParser streamTitleParser) {
        this.url = url;
        this.streamTitleParser = streamTitleParser;
    }

    public String getUrl() {
        return url;
    }

    public IcyStreamTitleParser getStreamTitleParser() {
        return streamTitleParser;
    }
}
