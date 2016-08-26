package com.goeuro.client;

import feign.Response;
import feign.codec.ErrorDecoder;


public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new GoEuroApiException(response.status() + " " + response.reason());
    }
}
