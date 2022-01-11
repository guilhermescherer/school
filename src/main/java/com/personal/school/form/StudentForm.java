package com.personal.school.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StudentForm extends PeopleForm {

    @NotNull @NotEmpty
    private Boolean isScholarshipHolder;
    private Long schoolClass;
}
