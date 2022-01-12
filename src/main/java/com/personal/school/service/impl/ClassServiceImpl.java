package com.personal.school.service.impl;

import com.personal.school.model.Class;
import com.personal.school.repository.ClassRepository;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<Class> getClassesByListId(List<Long> idList){
        List<Class> classes = new ArrayList<>();
        if(nonNull(idList)){
            classes = classRepository.findAllById(idList);

            if(idList.size() != classes.size()) {
                throw new EmptyResultDataAccessException("Class not found", idList.size());
            }
        }
        return classes;
    }

    @Override
    public Class getClassById(Long idClass){
        if(nonNull(idClass)){
            Optional<Class> schoolClass = classRepository.findById(idClass);
            return schoolClass.orElse(null);
        }
        return null;
    }

}
