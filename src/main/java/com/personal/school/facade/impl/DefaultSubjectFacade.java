package com.personal.school.facade.impl;

import com.personal.school.converter.Converter;
import com.personal.school.converter.impl.SubjectConverter;
import com.personal.school.facade.Facade;
import com.personal.school.facade.SubjectFacade;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class DefaultSubjectFacade implements SubjectFacade {

    private final SubjectService subjectService;
    private final Converter<SubjectForm, Subject> subjectConverter;

    @Autowired
    public DefaultSubjectFacade(SubjectService subjectService) {
        this.subjectService = subjectService;
        this.subjectConverter = new SubjectConverter();
    }

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
        final Subject subject = subjectConverter.convert(subjectForm);
        return subjectService.save(subject);
    }

    @Override
    public void remove(Long id) {
        Subject subject = subjectService.getById(id);
        subjectService.remove(subject);
    }

    @Override
    public Subject update(Long id, SubjectForm subjectForm) {
        Subject subject = subjectService.getById(id);
        return subjectConverter.convert(subject, subjectForm);
    }
}
