package com.personal.school.validator.impl;

import com.personal.school.enums.UpdateSalaryType;
import com.personal.school.exception.UnprocessableEntityException;
import com.personal.school.form.UpdateSalaryForm;
import com.personal.school.model.Teacher;
import com.personal.school.validator.AbstractUpdateSalary;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.personal.school.enums.UpdateSalaryType.PERCENTAGE;

public class UpdateSalaryPercentage extends AbstractUpdateSalary {

    private final static BigDecimal BIG_SALARY = new BigDecimal("10000");

    private final Map<String, Integer> salaryTypes = new HashMap<>(){{
       put("BIG_SALARY", 10);
       put("LOW_SALARY", 25);
    }};

    @Override
    public boolean check(Teacher teacher, UpdateSalaryForm form) {
        final UpdateSalaryType updateSalaryType = UpdateSalaryType.valueOf(form.getUpdateSalaryType());

        if(updateSalaryType.equals(PERCENTAGE)) {
            int maxPercentage = getMaxPercentageByType(teacher.getSalary());
            int percentage = Integer.parseInt(form.getValue());

            if(percentage > maxPercentage) {
                throw new UnprocessableEntityException("O valor de porcentagem é maior do que o permitido para este" +
                        "tipo de salário. Valor máximo permitido: " + maxPercentage);
            }
        }

        return checkNext(teacher, form);
    }

    private int getMaxPercentageByType(BigDecimal salary) {
        return salary.compareTo(BIG_SALARY) > 0 ? salaryTypes.get("BIG_SALARY") : salaryTypes.get("LOW_SALARY");
    }
}
