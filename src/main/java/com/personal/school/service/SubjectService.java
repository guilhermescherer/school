package com.personal.school.service;

import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAll();

    List<Subject> getAllById(List<Long> subjects);

    Subject getById(Long id);

    Subject save(SubjectForm subjectForm);

    void remove(Subject subject);

    Subject update(Subject id, SubjectForm subjectForm);
}
