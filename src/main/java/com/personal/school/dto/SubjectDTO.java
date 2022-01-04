package com.personal.school.dto;

import com.personal.school.model.Subject;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SubjectDTO {

    private Long id;
    private String name;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }

    public static List<SubjectDTO> toDto(List<Subject> subjects) {
        return subjects.stream()
                .map(SubjectDTO::new)
                .collect(Collectors.toList());
    }
}
