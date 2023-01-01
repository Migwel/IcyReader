package dev.migwel.icyreader;

public enum Sources {

    //BE
    MAXIMUMFM("https://n01a-eu.rcs.revma.com/vnmbzemrmm0uv"),

    //NL
    NPORADIO1("https://icecast.omroep.nl/radio1-bb-mp3"),
    NPORADIO2("https://icecast.omroep.nl/radio2-bb-mp3"),
    NPORADIO5("https://icecast.omroep.nl/radio5-bb-mp3"),
    RADIO538("https://25703.live.streamtheworld.com/RADIO538.mp3"),

    //US
    KEXP("https://kexp-mp3-128.streamguys1.com/kexp128.mp3");

    private final String url;

    Sources(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
