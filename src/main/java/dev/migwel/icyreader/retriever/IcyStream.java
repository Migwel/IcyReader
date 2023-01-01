package dev.migwel.icyreader.retriever;

import java.io.InputStream;

public record IcyStream(InputStream stream, String icyMetaInt) {}
