package com.goeuro.client;

import java.util.List;


public interface GoEuroApiClient {

    List<? extends PositionProvider> query(String query);
}
