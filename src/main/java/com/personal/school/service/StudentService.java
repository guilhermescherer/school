package com.personal.school.service;

import com.personal.school.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();

    List<Student> getAllById(List<Long> students);

    Student getById(Long id);

    Student save(Student student);

    void remove(Student student);
}
