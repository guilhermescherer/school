package com.personal.school.service.impl;

import com.personal.school.enums.UpdateSalaryType;
import com.personal.school.service.SalaryService;

import java.math.BigDecimal;

import static com.personal.school.utils.CalcUtils.getValueWithPercentage;

public class DefaultSalaryService implements SalaryService {

    @Override
    public BigDecimal updateSalary(BigDecimal currentSalary, UpdateSalaryType type, BigDecimal reajust) {
        BigDecimal salary;
        switch (type) {
            case PERCENTAGE:
                salary = getValueWithPercentage(currentSalary, reajust);
                break;
            case SUM:
                salary = currentSalary.add(reajust);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return salary;
    }
}
