package com.personal.school.service;

import com.personal.school.enums.UpdateSalaryType;

import java.math.BigDecimal;

public interface SalaryService {

    BigDecimal updateSalary(BigDecimal currentSalary, UpdateSalaryType type, BigDecimal value);
}
