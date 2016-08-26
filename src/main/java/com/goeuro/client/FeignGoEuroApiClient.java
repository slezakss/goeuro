package com.goeuro.client;

import feign.Param;
import feign.RequestLine;
import java.util.List;


public interface FeignGoEuroApiClient extends GoEuroApiClient {

    @RequestLine("GET position/suggest/en/{query}")
    List<GoEuroPosition> query(@Param("query") String query);
}
