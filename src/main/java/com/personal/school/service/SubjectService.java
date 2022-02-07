package com.personal.school.service;

import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<Subject> getAll();

    List<Subject> getAllByIdThrow(List<Long> subjects);

    Subject getByIdThrow(Long id);

    Optional<Subject> getById(Long id);

    Subject save(SubjectForm subjectForm);

    void remove(Long id);

    Subject update(Long id, SubjectForm subjectForm);
}
