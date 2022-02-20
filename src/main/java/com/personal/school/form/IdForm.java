package com.personal.school.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class IdForm {

    @NotNull
    private List<Long> ids;
}
