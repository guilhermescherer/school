package com.personal.school.dto;

import com.personal.school.model.Class;
import com.personal.school.model.Student;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.personal.school.utils.FormatterUtils.getDateDefaultFormatter;

@Getter
public class StudentDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final String telephone;
    private final String documentNumber;
    private final String birthDate;
    private final Boolean isScholarshipHolder;
    private final Class schoolClass;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.telephone = student.getTelephone();
        this.documentNumber = student.getDocumentNumber();
        this.birthDate = getDateDefaultFormatter(student.getBirthDate());
        this.isScholarshipHolder = student.getIsScholarshipHolder();
        this.schoolClass = student.getSchoolClass();
    }

    public static List<StudentDTO> toDto(List<Student> students) {
        return students.stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

}
