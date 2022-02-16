package com.personal.school.facade;

import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;

import java.util.List;

public interface ClassFacade {

    List<Class> getAll();

    Class getById(Long id);

    Class save(ClassForm classForm);

    void remove(Long id);

    Class update(Long id, ClassForm classForm);
}
