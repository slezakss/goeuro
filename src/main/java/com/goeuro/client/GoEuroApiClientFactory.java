package com.goeuro.client;

import feign.Feign;
import feign.gson.GsonDecoder;


public final class GoEuroApiClientFactory {

    private static final String ROOT_PATH = "http://api.goeuro.com/api/";
    private static final String VERSION = "v2";

    private GoEuroApiClientFactory() {
    }

    public static GoEuroApiClient defaultClient() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .errorDecoder(new CustomErrorDecoder())
                .target(FeignGoEuroApiClient.class, String.format("%s%s/", ROOT_PATH, VERSION));
    }
}
