package com.personal.school.form;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class SubjectForm {

    @NotNull @NotEmpty @Length(min=5, max=50)
    private String name;

}
