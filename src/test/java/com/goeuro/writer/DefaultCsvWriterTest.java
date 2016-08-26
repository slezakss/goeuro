package com.goeuro.writer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVPrinter;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class DefaultCsvWriterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    DefaultCsvWriter.PathProvider pathProvider;
    DefaultCsvWriter.CSVPrinterProvider csvPrinterProvider;
    CsvWriter writer;

    @Before
    public void setUp() throws IOException {
        pathProvider = Mockito.mock(DefaultCsvWriter.PathProvider.class);
        csvPrinterProvider = Mockito.mock(DefaultCsvWriter.CSVPrinterProvider.class);
        writer = new DefaultCsvWriter(pathProvider, csvPrinterProvider);
    }

    @Test
    public void shouldThrowWhenExceptionCreatingFile() throws IOException {
        when(pathProvider.get()).thenThrow(new IOException("File not found"));

        exception.expect(CsvException.class);
        exception.expectMessage("File not found");
        final Path result = writer.writePositions(Lists.emptyList());
    }

    @Test
    public void shouldThrowWhenExceptionCreatingCsvWriter() throws IOException {
        Path path = Paths.get("path");
        when(pathProvider.get()).thenReturn(path);

        when(csvPrinterProvider.get(eq(path))).thenThrow(new IOException("Problem with csv"));

        exception.expect(CsvException.class);
        exception.expectMessage("Problem with csv");
        final Path result = writer.writePositions(Lists.emptyList());
    }

}
