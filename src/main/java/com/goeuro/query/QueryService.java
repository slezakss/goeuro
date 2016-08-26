package com.goeuro.query;

import com.goeuro.Position;
import java.util.List;


public interface QueryService {

    List<Position> query(String query);
}
