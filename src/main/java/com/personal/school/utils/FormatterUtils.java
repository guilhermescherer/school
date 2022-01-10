package com.personal.school.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class FormatterUtils {

    public static final String DEFAULT_DATE_FORMATTER = "dd/MM/yyyy";
    public final static String DEFAULT_SCHOOLING_REGEX = "IN_PROGRESS|GRADUTATE|POST_GRADUTE|MASTER|DOCTORATE";
    public static final String STRING_REGEX_TELEPHONE = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$";

    public static DateTimeFormatter getDefaultDateFormatter(){
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER);
    }

    public static String getDateDefaultFormatter(LocalDate date){
        return date.format(getDefaultDateFormatter()).toString();
    }

    public static Pattern getPatternTelephoneBrazil(){
        return Pattern.compile(STRING_REGEX_TELEPHONE);
    }

}
