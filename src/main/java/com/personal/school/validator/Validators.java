package com.personal.school.validator;

import com.personal.school.validator.impl.UpdateSalaryPercentage;
import com.personal.school.validator.impl.UpdateSalarySum;

public class Validators {

    private static final UpdateSalarySum updateSalarySum = new UpdateSalarySum();
    private static final UpdateSalaryPercentage updateSalaryPercentage = new UpdateSalaryPercentage();

    public static AbstractUpdateSalary getUpdateSalaryValidator() {
        return updateSalarySum.linkWith(updateSalaryPercentage);
    }
}
