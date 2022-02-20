package com.personal.school.dto;

import com.personal.school.model.Teacher;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.personal.school.utils.FormatterUtils.getDateDefaultFormatter;

@Getter
public class TeacherDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final String telephone;
    private final String cpf;
    private final String birthDate;
    private final String schooling;
    private final String salary;

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.telephone = teacher.getTelephone();
        this.cpf = teacher.getCpf();
        this.birthDate = getDateDefaultFormatter(teacher.getBirthDate());
        this.schooling = teacher.getSchooling().name();
        this.salary = teacher.getSalary().toString();
    }

    public static List<TeacherDTO> toDto(List<Teacher> teachers){
        return teachers.stream()
                .map(TeacherDTO::new)
                .collect(Collectors.toList());
    }

}
