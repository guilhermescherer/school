package com.personal.school.form;

import com.personal.school.model.Student;
import com.personal.school.service.StudentService;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
public class StudentForm extends PeopleForm {

    @NotNull
    private Boolean isScholarshipHolder;
    @NotNull
    private Long schoolClass;

    public Student update(Long id, StudentService studentService) {
        Optional<Student> student = studentService.getById(id);
        // TODO: Fazer a parte de atualizar o student
        return student.get();
    }
}
