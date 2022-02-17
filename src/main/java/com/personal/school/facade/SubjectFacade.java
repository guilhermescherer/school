package com.personal.school.facade;

import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;

import java.util.List;

public interface SubjectFacade {

    List<Subject> getAll();

    Subject getById(Long id);

    Subject save(SubjectForm subjectForm);

    void remove(Long id);

    Subject update(Long id, SubjectForm subjectForm);

}
