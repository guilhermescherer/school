package com.personal.school.form;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.personal.school.utils.FormatterUtils.*;

@Getter
public class PeopleForm {

    @NotNull @NotEmpty @Length(min=3, max=50)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = STRING_REGEX_TELEPHONE)
    private String telephone;
    @NotNull @NotEmpty @CPF
    private String cpf;
    @NotNull @NotEmpty @Pattern(regexp = DEFAULT_DATE_FORMATTER)
    private String birthDate;
    @NotNull @NotEmpty
    private String address;
    @NotNull @NotEmpty @Pattern(regexp = STRING_REGEX_ZIP_CODE)
    private String zipCode;
}
