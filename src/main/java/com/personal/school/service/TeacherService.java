package com.personal.school.service;

import com.personal.school.form.TeacherForm;
import com.personal.school.form.TeacherUpdateForm;
import com.personal.school.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> getAll();

    List<Teacher> getAllByIdThrow(List<Long> teachers);

    Teacher getByIdThrow(Long id);

    Optional<Teacher> getById(Long id);

    Teacher save(TeacherForm teacherForm);

    void remove(Long id);

    Teacher update(Long id, TeacherUpdateForm teacherUpdateForm);

    void reajustSalary(Long id, String percentage);
}
