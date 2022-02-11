package com.personal.school.service.impl;

import com.personal.school.converter.SubjectConverter;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.repository.SubjectRepository;
import com.personal.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class DefaultSubjectService implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectConverter subjectConverter;

    @Override
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getAllByIdThrow(List<Long> ids) {
        if(isNull(ids)) return EMPTY_LIST;

        List<Subject> subjects = subjectRepository.findAllById(ids);
        if(ids.size() != subjects.size()) {
            throw new EmptyResultDataAccessException("Not found all subjects", ids.size());
        }

        return subjects;
    }

    @Override
    public Subject getByIdThrow(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(!subject.isPresent()){
            throw new EmptyResultDataAccessException("Not found subject", toIntExact(id));
        }
        return subject.get();
    }

    @Override
    public Optional<Subject> getById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject save(SubjectForm subjectForm) {
        Subject subject = subjectConverter.toSubject(subjectForm);
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public void remove(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject update(Long id, SubjectForm subjectForm) {
        Subject subject = getByIdThrow(id);
        return subjectConverter.toSubject(subject, subjectForm);
    }
}
