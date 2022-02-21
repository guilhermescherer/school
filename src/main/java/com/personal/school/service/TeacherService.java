package com.personal.school.service;

import com.personal.school.form.ReajustSalaryForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAll();

    List<Teacher> getAllById(List<Long> teachers);

    Teacher getById(Long id);

    Teacher save(Teacher teacher);

    void saveSubjects(Teacher teacher, List<Subject> subjects);

    void remove(Teacher teacher);

    void removeSubjects(Teacher teacher, List<Subject> subjects);

    void updateSalary(Teacher teacher, ReajustSalaryForm reajustSalaryForm);
}
