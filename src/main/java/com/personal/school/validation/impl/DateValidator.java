package com.personal.school.validation.impl;

import com.personal.school.validation.Date;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

public class DateValidator implements ConstraintValidator<Date, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(date)) return true;

        try {
            LocalDate.parse(date, getDefaultDateFormatter());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }
}
