package dev.migwel.icyreader.retriever;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class HttpIcyStream extends IcyStream {
    private final CloseableHttpResponse httpResponse;

    public HttpIcyStream(InputStream stream, String icyMetaInt, CloseableHttpResponse httpResponse) {
        super(stream, icyMetaInt);
        this.httpResponse = httpResponse;
    }

    @Override
    public void close() throws IOException {
        super.close();
        httpResponse.close();
    }
}
