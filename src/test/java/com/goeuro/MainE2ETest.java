package com.goeuro;

import com.goeuro.query.QueryException;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MainE2ETest {

    PrintStream printStream;
    ByteArrayOutputStream outputStream;

    @Before
    public  void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        Main.OUT = printStream;
    }

    @After
    public void cleanUp() throws IOException {
        printStream.close();
        outputStream.close();
        Main.OUT = System.out;
    }

    @Test
    public void shouldQueryAboutBerlinAndCreateFile() throws IOException {
        Main.main(new String[] {"Berlin"});
        final String output = outputStream.toString();

        assertThat(output).contains("Query results saved in:");
        removeFile(output);
    }

    @Test
    public void shouldQueryAboutBerlinAndCreateFileWithRecords() throws IOException {
        Main.main(new String[] {"Berlin"});
        final String output = outputStream.toString();

        final String filename = getFileName(output);
        CSVParser parser = new CSVParser(new FileReader(filename), CSVFormat.DEFAULT);

        assertThat(parser.getRecords()).isNotNull().isNotEmpty();
        removeFile(output);
    }

    @Test
    public void shouldPrintWarningWhenNoArguments() {
        Main.main(new String[] {});
        final String output = outputStream.toString();

        assertThat(output).contains("Wrong number of arguments");
    }

    @Test
    public void shouldPrintWarningWhenTooManyArguments() {
        Main.main(new String[] {"a", "b", "c"});
        final String output = outputStream.toString();

        assertThat(output).contains("Wrong number of arguments");
    }

    @Test
    public void shouldPrintErrorMessageWhenItOccurs() {
        Main.SERVICE = query -> {
            throw new QueryException("Error executing query");
        };
        Main.main(new String[] {"Berlin"});
        final String output = outputStream.toString();

        assertThat(output).contains("Error executing query");

        Main.SERVICE = new DefaultQueryFileService();
    }

    private void removeFile(String output) {
        Paths.get(getFileName(output)).toFile().delete();
    }

    private String getFileName(String output) {
        String filenamePart = output.split(":")[1];
        return filenamePart.substring(1, filenamePart.length() - 1);
    }

}