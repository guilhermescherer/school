package com.personal.school.service.impl;

import com.personal.school.model.Class;
import com.personal.school.repository.ClassRepository;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<Class> getAllByIdThrow(List<Long> ids){

        if(isNull(ids)) return EMPTY_LIST;

        List<Class> classes = classRepository.findAllById(ids);

        if(ids.size() != classes.size()) {
            throw new EmptyResultDataAccessException("Not found all classes", ids.size());
        }

        return classes;
    }

    public Class getByIdThrow(Long idClass) {
        Optional<Class> schoolClass = classRepository.findById(idClass);

        if(schoolClass.isPresent()){
            return schoolClass.get();
        } else {
            throw new EmptyResultDataAccessException("Not found class", toIntExact(idClass));
        }

    }

}
