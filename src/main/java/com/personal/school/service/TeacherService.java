package com.personal.school.service;

import com.personal.school.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> getAll();

    List<Teacher> getAllById(List<Long> teachers);

    Optional<Teacher> getById(Long id);

    void save(Teacher teacher);

    void remove(Long id);


}
