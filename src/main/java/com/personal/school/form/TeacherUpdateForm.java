package com.personal.school.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.personal.school.utils.FormatterUtils.ENUM_REGEX_SCHOOLING;

@Getter
public class TeacherUpdateForm extends PeopleUpdateForm {

    @NotEmpty
    @Pattern(regexp = ENUM_REGEX_SCHOOLING)
    private String schooling;
}
