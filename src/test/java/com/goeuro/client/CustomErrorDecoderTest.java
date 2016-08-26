package com.goeuro.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import java.util.HashMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class CustomErrorDecoderTest {

    @Test
    public void shouldCreateGoEuroApiExceptionFromResponse() {
        ErrorDecoder decoder = new CustomErrorDecoder();

        final Exception exception = decoder.decode("method",
                Response.builder()
                        .status(404)
                        .reason("Not found")
                        .headers(new HashMap<>())
                        .build()
        );

        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("404 Not found");
    }
}