package com.personal.school.dto;

import com.personal.school.model.Teacher;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.personal.school.utils.FormatterUtils.getDateDefaultFormatter;

@Getter
public class TeacherDetailsDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final String telephone;
    private final String cpf;
    private final String birthDate;
    private final String schooling;
    private final List<ClassDTO> classes;
    private final List<SubjectDTO> subjects;

    public TeacherDetailsDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.telephone = teacher.getTelephone();
        this.cpf = teacher.getCpf();
        this.birthDate = getDateDefaultFormatter(teacher.getBirthDate());
        this.schooling = teacher.getSchooling().name();
        this.subjects = SubjectDTO.toDto(teacher.getSubjects());
        this.classes = ClassDTO.toDto(teacher.getClasses());
    }

    public static List<TeacherDetailsDTO> toDto(List<Teacher> teachers){
        return teachers.stream()
                .map(TeacherDetailsDTO::new)
                .collect(Collectors.toList());
    }


}
