package com.personal.school.utils;

import br.com.caelum.stella.format.CPFFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatterUtils {

    public static final String DEFAULT_DATE_FORMATTER = "dd/MM/yyyy";

    public final static String ENUM_REGEX_SCHOOLING = "IN_PROGRESS|GRADUTATE|POST_GRADUTE|MASTER|DOCTORATE";
    public final static String ENUM_REGEX_TEACHING_TYPE = "ELEMENTARY_SCHOOL|HIGH_SCHOOL|UNIVERSITY_EDUCATION";

    public static final String STRING_REGEX_DATE = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    public static final String STRING_REGEX_TELEPHONE = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}-?[0-9]{4}$";
    public static final String STRING_REGEX_ZIP_CODE = "[0-9]{5}(-?)[0-9]{3}";

    public static DateTimeFormatter getDefaultDateFormatter(){
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER);
    }

    public static String getDateDefaultFormatter(LocalDate date){
        return date.format(getDefaultDateFormatter());
    }

    public static String getCpfUnformat(String cpf){
        CPFFormatter formatter = new CPFFormatter();
        return formatter.unformat(cpf);
    }
}
