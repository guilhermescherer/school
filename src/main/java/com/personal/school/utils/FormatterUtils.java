package com.personal.school.utils;

import br.com.caelum.stella.format.CPFFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Pattern;

public class FormatterUtils {

    public static final String DEFAULT_DATE_FORMATTER = "dd/MM/yyyy";
    public final static String DEFAULT_SCHOOLING_REGEX = "IN_PROGRESS|GRADUTATE|POST_GRADUTE|MASTER|DOCTORATE";
    public final static String DEFAULT_TEACHING_TYPE_REGEX = "ELEMENTARY_SCHOOL|HIGH_SCHOOL|UNIVERSITY_EDUCATION";
    public static final String STRING_REGEX_TELEPHONE = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}-?[0-9]{4}$";

    public static DateTimeFormatter getDefaultDateFormatter(){
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER);
    }

    public static String getDateDefaultFormatter(LocalDate date){
        return date.format(getDefaultDateFormatter());
    }

    public static Pattern getPatternTelephoneBrazil(){
        return Pattern.compile(STRING_REGEX_TELEPHONE);
    }

    public static String getCpfUnformat(String cpf){
        CPFFormatter formatter = new CPFFormatter();
        return formatter.unformat(cpf);
    }
}
