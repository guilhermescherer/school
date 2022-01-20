package com.personal.school.service;

import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    List<Class> getAllByIdThrow(List<Long> idList);

    Class getByIdThrow(Long idClass);

    List<Class> getAll();

    Optional<Class> getById(Long id);

    Class toClass(ClassForm classForm);

    void save(Class schoolClass);

    void remove(Long id);

    Class update(Long id, ClassForm classForm);
}
