package com.personal.school.dto;

import com.personal.school.model.Class;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClassDTO {

    private final Long id;
    private final String name;
    private final Integer qualificationNumber;
    private final String teachingType;

    public ClassDTO(Class schoolClass) {
        this.id = schoolClass.getId();
        this.name = schoolClass.getName();
        this.qualificationNumber = schoolClass.getQualificationNumber();
        this.teachingType = schoolClass.getTeachingType().name();
    }

    public static List<ClassDTO> toDto(List<Class> classes) {
        return classes.stream()
                .map(ClassDTO::new)
                .collect(Collectors.toList());
    }
}
