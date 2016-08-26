package com.goeuro.query;

import com.goeuro.Position;
import com.goeuro.client.GoEuroApiClient;
import com.goeuro.client.GoEuroApiClientFactory;
import com.goeuro.client.PositionProvider;
import java.util.List;
import java.util.stream.Collectors;


public class DefaultQueryService implements QueryService {

    private final GoEuroApiClient client;

    public DefaultQueryService() {
        client = GoEuroApiClientFactory.defaultClient();
    }

    public DefaultQueryService(GoEuroApiClient client) {
        this.client = client;
    }

    public List<Position> query(String query) {
        final List<? extends PositionProvider> positions;
        try {
            positions = client.query(query);
        } catch (Exception ex) {
            throw new QueryException("Error while getting results from server: " + ex.getMessage(), ex);
        }
        return positions.stream().map(PositionProvider::toPosition)
                .collect(Collectors.toList());
    }
}
