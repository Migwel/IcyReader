package dev.migwel.icyreader.retriever;

import java.io.IOException;
import java.io.InputStream;

public class IcyStream implements AutoCloseable {
    private final InputStream stream;
    private final String icyMetaInt;

    public IcyStream(InputStream stream, String icyMetaInt) {
        this.stream = stream;
        this.icyMetaInt = icyMetaInt;
    }

    public InputStream getStream() {
        return stream;
    }

    public String getIcyMetaInt() {
        return icyMetaInt;
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
