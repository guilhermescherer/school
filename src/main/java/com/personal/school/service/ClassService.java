package com.personal.school.service;

import com.personal.school.model.Class;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    List<Class> getAllByIdThrow(List<Long> idList);

    Class getByIdThrow(Long idClass);

    List<Class> getAll();

    Optional<Class> getById(Long id);

}
