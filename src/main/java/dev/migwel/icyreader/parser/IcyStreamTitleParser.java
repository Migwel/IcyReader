package dev.migwel.icyreader.parser;

import dev.migwel.icyreader.SongInfo;

import javax.annotation.CheckForNull;

public interface IcyStreamTitleParser {

    @CheckForNull
    SongInfo parse(String streamTitle);
}
