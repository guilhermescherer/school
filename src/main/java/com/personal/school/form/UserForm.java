package com.personal.school.form;

import com.personal.school.form.groups.Add;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UserForm {

    @NotNull(groups = Add.class) @NotEmpty @Length(min=3, max=50)
    private String name;
    @Email
    private String email;
    @NotNull(groups = Add.class) @Length(min=6, max=18)
    private String password;
    @NotNull(groups = Add.class)
    private List<Long> roles;

}
