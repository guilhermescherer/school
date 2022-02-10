package com.personal.school.form;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.personal.school.utils.FormatterUtils.*;

@Getter
public class PeopleUpdateForm {

    @NotEmpty
    @Length(min=3, max=50)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = STRING_REGEX_TELEPHONE)
    private String telephone;
    @NotEmpty @CPF
    private String cpf;
    @NotEmpty @Pattern(regexp = STRING_REGEX_DATE)
    private String birthDate;
    @NotEmpty
    private String address;
    @NotEmpty @Pattern(regexp = STRING_REGEX_ZIP_CODE)
    private String zipCode;
}
