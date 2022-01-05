package com.personal.school.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PeopleForm {

    @NotNull @NotEmpty @Length(min=3, max=50)
    private String name;
    @NotNull @NotEmpty @Email
    private String email;
    // TODO: Criar Bean Validation de Telefone
    private String telephone;
    @NotNull @NotEmpty
    private String documentNumber;
    // TODO: Criar Bean Validation de Data
    private LocalDate birthDate;

}
