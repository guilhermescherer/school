package com.personal.school.form;

import com.personal.school.form.groups.Add;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StudentForm extends PeopleForm {

    @NotNull(groups = Add.class)
    private Boolean isScholarshipHolder;
}
