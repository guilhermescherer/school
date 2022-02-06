package com.personal.school.form;

import com.personal.school.validation.Date;
import com.personal.school.validation.Telephone;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class PeopleForm {

    @NotNull @NotEmpty @Length(min=3, max=50)
    private String name;
    @Email
    private String email;
    @Telephone
    private String telephone;
    @NotNull @NotEmpty @CPF
    private String cpf;
    @NotNull @NotEmpty @Date
    private String birthDate;

}
