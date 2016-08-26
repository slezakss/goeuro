package com.goeuro;

import com.goeuro.query.DefaultQueryService;
import com.goeuro.query.QueryService;
import com.goeuro.writer.CsvWriter;
import com.goeuro.writer.DefaultCsvWriter;
import java.nio.file.Path;
import java.util.List;


public class DefaultQueryFileService implements QueryFileService {

    private final QueryService queryService;
    private final CsvWriter csvWriter;

    public DefaultQueryFileService() {
        queryService = new DefaultQueryService();
        csvWriter = new DefaultCsvWriter();
    }

    public DefaultQueryFileService(QueryService queryService, CsvWriter csvWriter) {
        this.queryService = queryService;
        this.csvWriter = csvWriter;
    }

    @Override
    public Path createQueryFile(String query) {
        final List<Position> positions = queryService.query(query);
        return csvWriter.writePositions(positions);
    }
}
