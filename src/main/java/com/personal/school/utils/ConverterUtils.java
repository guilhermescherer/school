package com.personal.school.utils;

import com.personal.school.enums.ConvertMethod;

import java.io.BufferedReader;
import java.io.IOException;

public class ConverterUtils {

    public static boolean isValidSet(Object field, ConvertMethod method) {
        return method.equals(ConvertMethod.ADD) || field != null;
    }

    public static String getStringByBufferedReader(BufferedReader bufferedReader) throws IOException {
        String response;
        StringBuilder json = new StringBuilder();

        while ((response = bufferedReader.readLine()) != null) {
            json.append(response);
        }
        return json.toString();
    }
}
