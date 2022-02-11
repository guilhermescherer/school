package com.personal.school.utils;

import com.personal.school.converter.ConvertMethod;

public class ConverterUtils {

    public static boolean isValidSet(String field, ConvertMethod method) {
        return method.equals(ConvertMethod.ADD) || field != null;
    }
}
