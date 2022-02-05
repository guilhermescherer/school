package com.personal.school.validation.impl;

import com.personal.school.validation.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

public class DateValidator implements ConstraintValidator<Date, String> {

    private static final Logger LOG = Logger.getLogger(DateValidator.class);

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(date)) return true;

        try {
            LocalDate.parse(date, getDefaultDateFormatter());
            return true;
        } catch (DateTimeParseException e) {
            LOG.error(e);
            return false;
        }

    }
}
