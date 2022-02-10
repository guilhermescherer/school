package com.personal.school.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.personal.school.utils.FormatterUtils.ENUM_REGEX_SCHOOLING;

@Getter
@Setter
public class TeacherForm extends PeopleForm {

    @NotNull
    private Double salary;
    @NotNull @NotEmpty @Pattern(regexp = ENUM_REGEX_SCHOOLING)
    private String schooling;
}
