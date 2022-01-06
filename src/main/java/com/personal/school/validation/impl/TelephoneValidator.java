package com.personal.school.validation.impl;

import com.personal.school.validation.Telephone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.personal.school.utils.FormatterUtils.getPatternTelephoneBrazil;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    @Override
    public boolean isValid(String telephone, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.isBlank(telephone)) return true;

        return getPatternTelephoneBrazil().matcher(telephone).matches();

    }

}
