package com.goeuro.query;

import com.goeuro.Position;
import com.goeuro.client.GoEuroPosition;
import com.goeuro.query.DefaultQueryService;
import com.goeuro.query.QueryService;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


public class DefaultQueryServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void shouldReturnEmptyListWhenThereAreNoResults() {
        final QueryService service = new DefaultQueryService(query -> Lists.emptyList());
        final List<Position> result = service.query("Berlin");
        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    public void shouldReturnOneMatchingResult() {
        final GoEuroPosition found =  new GoEuroPosition(1L, "name", "type", new GoEuroPosition.GeoPosition(BigDecimal.ONE, BigDecimal.ZERO));
        final QueryService service = new DefaultQueryService(query -> Lists.newArrayList(found));
        final List<Position> result = service.query("Berlin");
        assertThat(result).isNotNull().hasSize(1);
        final Position returned = result.get(0);
        assertThat(returned.getId()).isEqualTo(found.getId());
        assertThat(returned.getName()).isEqualTo(found.getName());
        assertThat(returned.getType()).isEqualTo(found.getType());
        assertThat(returned.getLatitude()).isEqualTo(found.getGeoPosition().getLatitude());
        assertThat(returned.getLongitude()).isEqualTo(found.getGeoPosition().getLongitude());
    }

    @Test
    public void shouldThrowQueryExceptionWhenExceptionOccursWhileQueryingData() {
        final QueryService service = new DefaultQueryService(query -> {
            throw new RuntimeException("No connection");
        });
        exception.expect(QueryException.class);
        exception.expectMessage("Error while getting results from server: " + "No connection");
        service.query("Berlin");
    }

}