package com.personal.school.dto;

import com.personal.school.model.Subject;
import lombok.Getter;

import java.util.List;

import static com.personal.school.dto.TeacherDTO.toDto;

@Getter
public class SubjectDetailsDTO {

    private final Long id;
    private final String name;
    private final List<TeacherDTO> teachers;

    public SubjectDetailsDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.teachers = toDto(subject.getTeachers());
    }

}
