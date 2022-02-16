package com.personal.school.facade.impl;

import com.personal.school.facade.SubjectFacade;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultSubjectFacade implements SubjectFacade {

    @Autowired
    SubjectService subjectService;

    @Override
    public List<Subject> getAll() {
        return subjectService.getAll();
    }

    @Override
    public Subject getById(Long id) {
        return subjectService.getById(id);
    }

    @Override
    public Subject save(SubjectForm subjectForm) {
        return subjectService.save(subjectForm);
    }

    @Override
    public void remove(Long id) {
        Subject subject = subjectService.getById(id);
        subjectService.remove(subject);
    }

    @Override
    public Subject update(Long id, SubjectForm subjectForm) {
        Subject subject = subjectService.getById(id);
        return subjectService.update(subject, subjectForm);
    }
}
