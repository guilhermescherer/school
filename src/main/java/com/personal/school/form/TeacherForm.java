package com.personal.school.form;

import com.personal.school.form.groups.Add;
import com.personal.school.form.groups.Update;
import com.personal.school.validator.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import static com.personal.school.utils.FormatterUtils.ENUM_REGEX_SCHOOLING;

@Getter
@Setter
public class TeacherForm extends PeopleForm {

    @Null(groups = Update.class, message = "Salary must be null in Update")
    @NotNull(groups = Add.class)
    private Double salary;
    @NotNull(groups = Add.class)
    @NotEmpty @Pattern(regexp = ENUM_REGEX_SCHOOLING)
    private String schooling;
}
