package com.personal.school.service;

import com.personal.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAll();

    Optional<Student> getById(Long id);

    void remove(Long id);

    void save(Student student);

}
