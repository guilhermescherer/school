package com.personal.school.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StudentForm extends PeopleForm {

    @NotNull
    private Boolean isScholarshipHolder;
    @NotNull
    private Long schoolClass;

}
