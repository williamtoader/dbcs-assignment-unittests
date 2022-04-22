package com.example.dbcsassignmentunittests.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceLoader {
    static ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    public static String loadTestData(String name) throws Exception {
        InputStream stream = classloader.getResourceAsStream("test-data/" + name + ".json");
        if (stream == null) throw new Exception("Resource " + name + " not found");
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.lines().parallel().collect(Collectors.joining(""));
    }
}
