package com.personal.school.service;

import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    List<Class> getAll();

    List<Class> getAllByIdThrow(List<Long> idList);

    Class getByIdThrow(Long idClass);

    Optional<Class> getById(Long id);

    Class save(ClassForm classForm);

    void remove(Long id);

    Class update(Long id, ClassForm classForm);
}
