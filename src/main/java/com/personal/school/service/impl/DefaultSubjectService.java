package com.personal.school.service.impl;

import com.personal.school.converter.Converter;
import com.personal.school.exception.NotFoundException;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.repository.SubjectRepository;
import com.personal.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class DefaultSubjectService implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    Converter<SubjectForm, Subject> subjectConverter;

    @Override
    public List<Subject> getAll(){
        final List<Subject> subjects = subjectRepository.findAll();
        if(isEmpty(subjects)) {
            throw new NotFoundException("Not found any subject");
        }
        return subjects;
    }

    @Override
    public List<Subject> getAllById(@NotNull List<Long> ids) {
        List<Subject> subjects = subjectRepository.findAllById(ids);
        if(ids.size() != subjects.size()) {
            throw new NotFoundException("Not found all subjects");
        }
        return subjects;
    }

    @Override
    public Subject getById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(!subject.isPresent()){
            throw new NotFoundException("Not found subject");
        }
        return subject.get();
    }

    @Override
    public Subject save(SubjectForm subjectForm) {
        Subject subject = subjectConverter.convert(subjectForm);
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public void remove(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public Subject update(Subject subject, SubjectForm subjectForm) {
        return subjectConverter.convert(subject, subjectForm);
    }
}
