package com.goeuro;

import com.goeuro.query.QueryService;
import com.goeuro.writer.CsvWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


public class DefaultQueryFileServiceTest {

    QueryFileService service;
    QueryService queryService;
    CsvWriter csvWriter;


    @Before
    public void setUp() {
        queryService = Mockito.mock(QueryService.class);
        csvWriter = Mockito.mock(CsvWriter.class);
        service = new DefaultQueryFileService(queryService, csvWriter);
    }

    @Test
    public void shouldQueryAndWrite() {
        String query = "query";
        service.createQueryFile(query);
        Mockito.verify(queryService).query(eq(query));
        Mockito.verify(csvWriter).writePositions(any(List.class));
    }

    @Test
    public void shouldQueryAndWriteResult() {
        String query = "query";
        List<Position> positions = Lists.newArrayList(new Position(1L, null, null, null, null));
        when(queryService.query(eq(query))).thenReturn(positions);
        service.createQueryFile(query);
        Mockito.verify(queryService).query(eq(query));
        Mockito.verify(csvWriter).writePositions(eq(positions));
    }

    @Test
    public void shouldReturnFilePath() {
        String query = "query";
        Path path = Paths.get("path");
        when(csvWriter.writePositions(any(List.class))).thenReturn(path);
        final Path queryFile = service.createQueryFile(query);

        assertThat(queryFile).isEqualTo(path);
    }

}