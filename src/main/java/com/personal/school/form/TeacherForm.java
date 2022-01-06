package com.personal.school.form;

import com.personal.school.validation.Schooling;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class TeacherForm extends PeopleForm {

    @NotNull @NotEmpty @Schooling
    private String schooling;

}
