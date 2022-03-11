package com.personal.school.validator.impl;

import com.personal.school.enums.UpdateSalaryType;
import com.personal.school.exception.UnprocessableEntityException;
import com.personal.school.form.UpdateSalaryForm;
import com.personal.school.model.Teacher;
import com.personal.school.validator.AbstractUpdateSalary;

import java.math.BigDecimal;

import static com.personal.school.enums.UpdateSalaryType.SUM;

public class UpdateSalarySum extends AbstractUpdateSalary {

    private final static String MAX_VALUE_UPDATE_SALARY = "5000";

    @Override
    public boolean check(Teacher teacher, UpdateSalaryForm form) {
        final UpdateSalaryType updateSalaryType = UpdateSalaryType.valueOf(form.getUpdateSalaryType());

        if(updateSalaryType.equals(SUM) && isInvalidSalaryValue(form)) {
            throw new UnprocessableEntityException("O valor de reajuste não pode ser maior que 5000 quando o tipo for soma");
        }

        return checkNext(teacher, form);
    }

    private boolean isInvalidSalaryValue(UpdateSalaryForm form) {
        final BigDecimal updateSalaryAmount = new BigDecimal(form.getValue());
        return updateSalaryAmount.compareTo(new BigDecimal(MAX_VALUE_UPDATE_SALARY)) > 0;
    }
}
