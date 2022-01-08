package com.personal.school.form;

import com.personal.school.validation.Schooling;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class TeacherForm extends PeopleForm {

    @NotNull @NotEmpty @Schooling
    private String schooling;
    private List<Long> subjects;

}
