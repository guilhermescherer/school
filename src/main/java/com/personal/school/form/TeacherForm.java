package com.personal.school.form;

import com.personal.school.validation.Schooling;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TeacherForm extends PeopleForm {

    @NotNull @NotEmpty @Schooling
    private String schooling;

}
