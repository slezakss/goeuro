package com.goeuro;

import java.math.BigDecimal;


public class Position {
    private final Long id;
    private final String name;
    private final String type;
    private final BigDecimal latitude;
    private final BigDecimal longitude;

    public Position(Long id, String name, String type, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String[] values() {
        return new String[]{id.toString(), name, type, latitude.toString(), longitude.toString()};
    }
}
