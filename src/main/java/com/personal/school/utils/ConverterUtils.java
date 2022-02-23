package com.personal.school.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class ConverterUtils {

    public static String getStringByBufferedReader(BufferedReader bufferedReader) throws IOException {
        String response;
        StringBuilder json = new StringBuilder();

        while ((response = bufferedReader.readLine()) != null) {
            json.append(response);
        }
        return json.toString();
    }

}
