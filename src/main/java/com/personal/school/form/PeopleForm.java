package com.personal.school.form;

import com.personal.school.form.groups.Add;
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

    @NotNull(groups = Add.class)
    @NotEmpty
    @Length(min=3, max=50)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = STRING_REGEX_TELEPHONE)
    private String telephone;
    @NotNull(groups = Add.class)
    @NotEmpty
    @CPF
    private String cpf;
    @NotNull(groups = Add.class)
    @NotEmpty
    @Pattern(regexp = STRING_REGEX_DATE)
    private String birthDate;
    @NotNull(groups = Add.class)
    @NotEmpty
    private String address;
    @NotNull(groups = Add.class)
    @NotEmpty
    @Pattern(regexp = STRING_REGEX_ZIP_CODE)
    private String zipCode;
}
