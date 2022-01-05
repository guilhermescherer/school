package com.personal.school.utils;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class FormatterUtils {

    public static final String DEFAULT_DATE_FORMATTER = "dd/mm/yyyy";
    public static final String STRING_REGEX_TELEPHONE = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$";

    public static DateTimeFormatter getDefaultDateFormatter(){
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER);
    }

    public static Pattern getPatternTelephoneBrazil(){
        return Pattern.compile(STRING_REGEX_TELEPHONE);
    }

}
