package dev.migwel.icyreader.retriever;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HttpIcyStreamRetriever implements IcyStreamRetriever {

    private static final Logger log = LogManager.getLogger(HttpIcyStreamRetriever.class);

    private final CloseableHttpClient httpClient;

    public HttpIcyStreamRetriever() {
        this(HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(StandardCookieSpec.RELAXED).build())
                .build());
    }

    public HttpIcyStreamRetriever(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public IcyStream retrieve(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Icy-MetaData", "1");
        httpGet.addHeader("Connection", "close");
        httpGet.addHeader("Accept", "");
        try {
            ClassicHttpResponse response = httpClient.execute(httpGet);
            if (response.getCode() != 200) {
                log.warn("Could not fetch stream. Status line: " + response.getCode());
                return null;
            }
            String icyMetaInt = retrieveIcyMetaInt(response);
            return new HttpIcyStream(response.getEntity().getContent(), icyMetaInt, response);
        } catch (IOException e) {
            log.warn("An exception occurred while fetching the stream", e);
            return null;
        }
    }

    private String retrieveIcyMetaInt(HttpResponse response) {
        Header icyMetaIntHeader = response.getFirstHeader("icy-metaint");
        return icyMetaIntHeader.getValue();
    }
}
