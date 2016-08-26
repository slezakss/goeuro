package com.goeuro.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GoEuroApiClientFactoryTest {

    @Test
    public void shouldCreateDefaultClient() {
        final GoEuroApiClient client = GoEuroApiClientFactory.defaultClient();
        assertThat(client).isNotNull();
    }

}