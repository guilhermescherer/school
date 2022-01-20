package com.personal.school.service;

import com.personal.school.form.TeacherForm;
import com.personal.school.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> getAll();

    List<Teacher> getAllByIdThrow(List<Long> teachers);

    Optional<Teacher> getById(Long id);

    void save(Teacher teacher);

    void remove(Long id);

    Teacher toTeacher(TeacherForm teacherForm, SubjectService subjectService, ClassService classService);

    Teacher update(Long id, TeacherForm teacherForm, SubjectService subjectService, ClassService classService);
}
