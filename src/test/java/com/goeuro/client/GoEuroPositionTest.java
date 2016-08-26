package com.goeuro.client;

import com.goeuro.Position;
import java.math.BigDecimal;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GoEuroPositionTest {

    GoEuroPosition provider = new GoEuroPosition(1L, "name", "type", new GoEuroPosition.GeoPosition(BigDecimal.ONE, BigDecimal.ZERO));

    @Test
    public void shouldConvertToPositionAndCopyId() {
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getId()).isEqualTo(provider.getId());
    }

    @Test
    public void shouldConvertToPositionAndCopyName() {
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getName()).isEqualTo(provider.getName());
    }

    @Test
    public void shouldConvertToPositionAndCopyType() {
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getType()).isEqualTo(provider.getType());
    }

    @Test
    public void shouldConvertToPositionAndCopyLatitude() {
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getLatitude()).isEqualTo(provider.getGeoPosition().getLatitude());
    }

    @Test
    public void shouldConvertToPositionAndCopylongitude() {
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getLongitude()).isEqualTo(provider.getGeoPosition().getLongitude());
    }

    @Test
    public void shouldConvertToPositionAndSetLatitudeToNullWhenGeoPositionMissing() {
        provider = new GoEuroPosition(1L, "name", "type", null);
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getLongitude()).isNull();
    }

    @Test
    public void shouldConvertToPositionAndSetLongitudeToNullWhenGeoPositionMissing() {
        provider = new GoEuroPosition(1L, "name", "type", null);
        final Position position = provider.toPosition();

        assertThat(position).isNotNull();
        assertThat(position.getLongitude()).isNull();
    }


}