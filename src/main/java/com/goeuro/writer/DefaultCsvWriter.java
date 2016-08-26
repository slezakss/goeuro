package com.goeuro.writer;

import com.goeuro.Position;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class DefaultCsvWriter implements CsvWriter {

    private static final CSVFormat FORMAT = CSVFormat.DEFAULT;
    private static final String FILENAME = "query.csv";

    private final PathProvider pathProvider;
    private final CSVPrinterProvider csvPrinterProvider;

    public DefaultCsvWriter() {
        this.pathProvider = () -> Files.createFile(Paths.get(UUID.randomUUID() + FILENAME));
        this.csvPrinterProvider = path -> new CSVPrinter(new FileWriter(path.toFile()), FORMAT);
    }

    public DefaultCsvWriter(PathProvider pathProvider, CSVPrinterProvider csvPrinterProvider) {
        this.pathProvider = pathProvider;
        this.csvPrinterProvider = csvPrinterProvider;
    }

    @Override
    public Path writePositions(List<Position> positions) {
        final Path path;
        try {
            path = pathProvider.get();
        } catch (IOException ex) {
            throw new CsvException("Error creating file: " + ex.getMessage(), ex);
        }
        try (final CSVPrinter csvPrinter = csvPrinterProvider.get(path)) {
            final List<String[]> records = positions.stream().map(Position::values).collect(Collectors.toList());
            csvPrinter.printRecords(records);
            csvPrinter.flush();
            return path;
        } catch (IOException ex) {
            throw new CsvException("Error while writing results to file: " + ex.getMessage(), ex);
        }
    }

    @FunctionalInterface
    public interface PathProvider {
        Path get() throws IOException;
    }

    @FunctionalInterface
    public interface CSVPrinterProvider {
        CSVPrinter get(Path path) throws IOException;
    }
}
