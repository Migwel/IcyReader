package dev.migwel.icyreader.retriever;

import javax.annotation.CheckForNull;

public interface IcyStreamRetriever {

    @CheckForNull
    IcyStream retrieve(String url);
}
