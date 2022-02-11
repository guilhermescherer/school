package com.personal.school.service;

import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAll();

    List<Student> getAllByIdThrow(List<Long> students);

    Student getByIdThrow(Long id);

    Optional<Student> getById(Long id);

    Student save(StudentForm studentForm);

    void remove(Long id);

    Student update(Long id, StudentForm studentUpdateForm);
}
