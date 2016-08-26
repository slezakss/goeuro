package com.goeuro;

import java.io.PrintStream;
import java.nio.file.Path;


public class Main {

    static QueryFileService SERVICE = new DefaultQueryFileService();
    static PrintStream OUT = System.out;

    public static void main(String[] args) {
        if (args.length != 1) {
            OUT.println("Wrong number of arguments, expected 1 (query string), got " + args.length);
            return;
        }
        final String query = args[0];
        try {
            final Path queryFilePath = SERVICE.createQueryFile(query);
            OUT.println("Query results saved in: " + queryFilePath.toString());
        } catch (Exception ex) {
            OUT.println(ex.getMessage());
        }
    }
}
