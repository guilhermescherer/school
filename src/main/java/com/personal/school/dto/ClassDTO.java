package com.personal.school.dto;

import com.personal.school.model.Class;
import lombok.Getter;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

import java.util.List;

@Getter
public class ClassDTO {

    public static List<ClassDTO> toDto(List<Class> classes) {
        return isNull(classes) ? EMPTY_LIST : EMPTY_LIST;
    }
}
