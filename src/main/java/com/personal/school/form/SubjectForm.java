package com.personal.school.form;

import com.personal.school.form.groups.Add;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class SubjectForm {

    @NotNull(groups = Add.class) @NotEmpty @Length(min=5, max=50)
    private String name;
}
