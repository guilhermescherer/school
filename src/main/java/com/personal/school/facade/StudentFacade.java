package com.personal.school.facade;

import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;

import java.util.List;

public interface StudentFacade {

    List<Student> getAll();

    List<Student> getAllById(List<Long> students);

    Student getById(Long id);

    Student save(StudentForm studentForm);

    void remove(Long id);

    Student update(Long id, StudentForm studentUpdateForm);
}
