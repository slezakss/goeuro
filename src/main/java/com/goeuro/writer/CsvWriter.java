package com.goeuro.writer;

import com.goeuro.Position;
import java.nio.file.Path;
import java.util.List;


public interface CsvWriter {

    Path writePositions(List<Position> position);

}
