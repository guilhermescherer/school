package com.personal.school.dto;

import com.personal.school.model.Subject;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SubjectDto {

    private Long id;
    private String name;

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }

    public static List<SubjectDto> toDto(List<Subject> subjects) {
        return subjects.stream()
                .map(SubjectDto::new)
                .collect(Collectors.toList());
    }
}
