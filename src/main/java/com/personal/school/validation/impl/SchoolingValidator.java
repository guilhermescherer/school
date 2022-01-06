package com.personal.school.validation.impl;

import com.personal.school.validation.Schooling;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.personal.school.model.Schooling.valueOf;

public class SchoolingValidator implements ConstraintValidator<Schooling, String> {

    @Override
    public boolean isValid(String schooling, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.isBlank(schooling)) return true;

        try {
            valueOf(schooling);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
