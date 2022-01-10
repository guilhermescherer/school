package com.personal.school.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.personal.school.utils.FormatterUtils.DEFAULT_SCHOOLING_REGEX;

@Getter
@Setter
public class TeacherForm extends PeopleForm {

    @NotNull @NotEmpty @Pattern(regexp = DEFAULT_SCHOOLING_REGEX)
    private String schooling;
    private List<Long> subjects;
    private List<Long> classes;

}
