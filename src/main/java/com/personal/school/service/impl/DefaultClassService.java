package com.personal.school.service.impl;

import com.personal.school.converter.ClassConverter;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.repository.ClassRepository;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class DefaultClassService implements ClassService {

    @Autowired
    ClassRepository classRepository;
    @Autowired
    ClassConverter classConverter;

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }

    @Override
    public List<Class> getAllById(List<Long> ids){
        if(isNull(ids)) return EMPTY_LIST;

        List<Class> classes = classRepository.findAllById(ids);

        if(ids.size() != classes.size()) {
            throw new EmptyResultDataAccessException("Not found all classes", ids.size());
        }

        return classes;
    }

    @Override
    public Class getById(Long id) {
        Optional<Class> schoolClass = classRepository.findById(id);

        if(schoolClass.isPresent()){
            return schoolClass.get();
        } else {
            throw new EmptyResultDataAccessException("Not found class", toIntExact(id));
        }
    }

    @Override
    public Class save(ClassForm classForm) {
        Class schoolClass = classConverter.toClass(classForm);
        classRepository.save(schoolClass);
        return schoolClass;
    }

    @Override
    public void remove(Class schoolClass) {
        classRepository.delete(schoolClass);
    }

    @Override
    public Class update(Long id, ClassForm classForm) {
        Class schoolClass = this.getById(id);
        classConverter.toClass(schoolClass, classForm);
        return schoolClass;
    }
}
