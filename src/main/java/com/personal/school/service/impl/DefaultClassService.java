package com.personal.school.service.impl;

import com.personal.school.converter.ClassConverter;
import com.personal.school.exception.NotFoundException;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.repository.ClassRepository;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class DefaultClassService implements ClassService {

    @Autowired
    ClassRepository classRepository;
    @Autowired
    ClassConverter classConverter;

    @Override
    public List<Class> getAll() {
        final List<Class> classes = classRepository.findAll();
        if(isEmpty(classes)) {
            throw new NotFoundException("Not found any class");
        }
        return classes;
    }

    @Override
    public List<Class> getAllById(@NotNull List<Long> ids){
        List<Class> classes = classRepository.findAllById(ids);

        if(ids.size() != classes.size()) {
            throw new NotFoundException("Not found all classes");
        }

        return classes;
    }

    @Override
    public Class getById(Long id) {
        Optional<Class> schoolClass = classRepository.findById(id);

        if(schoolClass.isPresent()){
            return schoolClass.get();
        } else {
            throw new NotFoundException("Not found class");
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
