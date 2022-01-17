package com.personal.school.service.impl;

import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.repository.SubjectRepository;
import com.personal.school.service.SubjectService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherService teacherService;

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
    public Optional<Subject> getById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    @Override
    public void remove(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject toSubject(SubjectForm subjectForm) {
        List<Teacher> teachers = teacherService.getAllByIdThrow(subjectForm.getTeachers());
        return new Subject(subjectForm.getName(), teachers);
    }

    @Override
    public Subject update(Long id, SubjectForm subjectForm) {
        Optional<Subject> subject = getById(id);

        if(subject.isPresent()){
            subject.get().setName(subjectForm.getName());
        } else {
            throw new EmptyResultDataAccessException("Not found subject", toIntExact(id));
        }

        return subject.get();
    }

}
