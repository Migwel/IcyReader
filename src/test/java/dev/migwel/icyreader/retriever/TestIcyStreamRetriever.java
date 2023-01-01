package dev.migwel.icyreader.retriever;

import java.io.InputStream;

public class TestIcyStreamRetriever implements IcyStreamRetriever {

    private final int icyMetaInt;

    public TestIcyStreamRetriever(int icyMetaInt) {
        this.icyMetaInt = icyMetaInt;
    }

    @Override
    public IcyStream retrieve(String url) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(url+ ".mp3");
        return new IcyStream(is, "["+ icyMetaInt +"]");
    }
}
