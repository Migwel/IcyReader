package dev.migwel.icyreader.retriever;

import org.apache.hc.core5.http.ClassicHttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class HttpIcyStream extends IcyStream {
    private final ClassicHttpResponse httpResponse;

    public HttpIcyStream(InputStream stream, String icyMetaInt, ClassicHttpResponse httpResponse) {
        super(stream, icyMetaInt);
        this.httpResponse = httpResponse;
    }

    @Override
    public void close() throws IOException {
        super.close();
        httpResponse.close();
    }
}
