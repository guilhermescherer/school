package com.personal.school.facade;

import com.personal.school.form.ClassForm;
import com.personal.school.form.IdForm;
import com.personal.school.model.Class;

import java.util.List;

public interface ClassFacade {

    List<Class> getAll();

    Class getById(Long id);

    Class save(ClassForm classForm);

    void remove(Long id);

    Class update(Long id, ClassForm classForm);

    void saveStudents(Long id, IdForm form);

    void saveTeachers(Long id, IdForm form);

    void removeStudents(Long id, IdForm form);

    void removeTeachers(Long id, IdForm form);
}
