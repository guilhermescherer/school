package com.personal.school.dto;

import com.personal.school.model.Class;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TeacherDTO {

    private String schooling;
    private List<ClassDTO> classes;
    private List<SubjectDTO> subjects;

    public TeacherDTO(Teacher teacher) {
        this.schooling = teacher.getSchooling().name();
        this.subjects = SubjectDTO.toDto(teacher.getSubjects());
    }

    public static List<TeacherDTO> toDto(List<Teacher> teachers){
        return teachers.stream()
                .map(TeacherDTO::new)
                .collect(Collectors.toList());
    }

}
