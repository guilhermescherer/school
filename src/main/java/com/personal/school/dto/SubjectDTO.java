package com.personal.school.dto;

import com.personal.school.model.Subject;
import lombok.Getter;

import static java.util.Collections.EMPTY_LIST;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Getter
public class SubjectDTO {

    private final Long id;
    private final String name;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }

    public static List<SubjectDTO> toDto(List<Subject> subjects) {
        return isNull(subjects) ? EMPTY_LIST : subjects.stream().map(SubjectDTO::new).collect(Collectors.toList());
    }
}
