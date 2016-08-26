package com.goeuro;

import java.math.BigDecimal;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PositionTest {

    @Test
    public void shouldPrepareValuesArray() {
        Position position = new Position(1L, "name", "type", BigDecimal.ONE, BigDecimal.ZERO);
        final String[] values = position.values();

        assertThat(values).isNotNull().hasSize(5);
        assertThat(values[0]).isEqualTo("1");
        assertThat(values[1]).isEqualTo("name");
        assertThat(values[2]).isEqualTo("type");
        assertThat(values[3]).isEqualTo("1");
        assertThat(values[4]).isEqualTo("0");
    }
}