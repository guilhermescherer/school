package com.personal.school.utils;

import java.math.BigDecimal;

public class CalcUtils {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal getValueWithPercentage(BigDecimal value, BigDecimal percentage) {
        return getPercentage(value, percentage).add(value);
    }

    public static BigDecimal getPercentage(BigDecimal value, BigDecimal percentage) {
        return value.multiply(percentage).divide(ONE_HUNDRED);
    }
}
