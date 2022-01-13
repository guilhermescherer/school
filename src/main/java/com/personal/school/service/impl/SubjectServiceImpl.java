package com.personal.school.service.impl;

import com.personal.school.model.Subject;
import com.personal.school.repository.SubjectRepository;
import com.personal.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getAllById(List<Long> ids) {
        List<Subject> subjects = subjectRepository.findAllById(ids);

        if(ids.size() != subjects.size()) {
            throw new EmptyResultDataAccessException("Not all subjects found", ids.size());
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

}
