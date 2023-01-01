package dev.migwel.icyreader.retriever;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HttpIcyStreamRetriever implements IcyStreamRetriever {

    private static final Logger log = LogManager.getLogger(HttpIcyStreamRetriever.class);

    private final HttpClient httpClient;

    public HttpIcyStreamRetriever() {
        this(HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
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
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                log.warn("Could not fetch stream. Status line: " + response.getStatusLine());
                return null;
            }
            String icyMetaInt = retrieveIcyMetaInt(response);
            return new IcyStream(response.getEntity().getContent(), icyMetaInt);
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
