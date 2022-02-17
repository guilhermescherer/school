package com.personal.school.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.personal.school.utils.FormatterUtils.ENUM_REGEX_TEACHING_TYPE;

@Getter
public class ClassForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull
    private Integer qualificationNumber;
    @NotNull @NotEmpty @Pattern(regexp = ENUM_REGEX_TEACHING_TYPE)
    private String teachingType;
    private List<Long> teachers;
    private List<Long> students;

}
