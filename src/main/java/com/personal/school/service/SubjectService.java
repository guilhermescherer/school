package com.personal.school.service;

import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<Subject> getAll();

    List<Subject> getAllByIdThrow(List<Long> subjects);

    Optional<Subject> getById(Long id);

    void save(Subject subject);

    void remove(Long id);

    Subject toSubject(SubjectForm subjectForm);

    Subject update(Long id, SubjectForm subjectForm);
}
