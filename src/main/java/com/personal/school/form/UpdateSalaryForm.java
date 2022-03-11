package com.personal.school.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.personal.school.utils.FormatterUtils.ENUM_REGEX_UPDATE_SALARY_TYPE;

@Getter
public class UpdateSalaryForm {

    @NotNull @NotEmpty
    private String value;
    @NotNull @NotEmpty @Pattern(regexp = ENUM_REGEX_UPDATE_SALARY_TYPE)
    private String updateSalaryType;

}
