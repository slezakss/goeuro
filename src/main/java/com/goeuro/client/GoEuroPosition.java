package com.goeuro.client;

import com.goeuro.Position;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

import static java.util.Objects.nonNull;


public class GoEuroPosition implements PositionProvider {

    @SerializedName("_id")
    private final Long id;
    private final String name;
    private final String type;
    @SerializedName("geo_position")
    private final GeoPosition geoPosition;

    public GoEuroPosition(Long id, String name, String type, GeoPosition geoPosition) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.geoPosition = geoPosition;
    }

    @Override
    public Position toPosition() {
        return new Position(id, name, type,
                nonNull(geoPosition) ? geoPosition.getLatitude() : null,
                nonNull(geoPosition) ? geoPosition.getLongitude() : null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public static class GeoPosition {
        private final BigDecimal latitude;
        private final BigDecimal longitude;

        public GeoPosition(BigDecimal latitude, BigDecimal longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public BigDecimal getLongitude() {
            return longitude;
        }

        public BigDecimal getLatitude() {
            return latitude;
        }
    }

}
