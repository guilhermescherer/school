package com.personal.school.dto;

import com.personal.school.model.Class;
import lombok.Getter;

import java.util.List;

@Getter
public class ClassDetailsDTO {

    private final Long id;
    private final String name;
    private final Integer qualificationNumber;
    private final String teachingType;
    private final List<TeacherDTO> teachers;
    private final List<StudentDTO> students;

    public ClassDetailsDTO(Class schoolClass) {
        this.id = schoolClass.getId();
        this.name = schoolClass.getName();
        this.qualificationNumber = schoolClass.getQualificationNumber();
        this.teachingType = schoolClass.getTeachingType().name();
        this.teachers = TeacherDTO.toDto(schoolClass.getTeachers());
        this.students = StudentDTO.toDto(schoolClass.getStudents());
    }

}
