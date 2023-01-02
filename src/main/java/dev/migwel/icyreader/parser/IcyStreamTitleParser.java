package dev.migwel.icyreader.parser;

import dev.migwel.icyreader.SongInfo;

import javax.annotation.Nonnull;

public interface IcyStreamTitleParser {

    @Nonnull
    SongInfo parse(String streamTitle);
}
