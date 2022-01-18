package com.personal.school.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class LoginForm {

    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String password;

}
