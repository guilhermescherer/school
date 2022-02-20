package com.personal.school.facade.impl;

import com.personal.school.facade.ClassFacade;
import com.personal.school.facade.Facade;
import com.personal.school.form.ClassForm;
import com.personal.school.form.IdForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.service.ClassService;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultClassFacade implements ClassFacade {

    @Autowired
    ClassService classService;
    @Autowired
    StudentService studentService;

    @Override
    public List<Class> getAll() {
        return classService.getAll();
    }

    @Override
    public Class getById(Long id) {
        return classService.getById(id);
    }

    @Override
    public Class save(ClassForm classForm) {
        return classService.save(classForm);
    }

    @Override
    public void remove(Long id) {
        final Class schoolClass = classService.getById(id);
        classService.remove(schoolClass);
    }

    @Override
    public Class update(Long id, ClassForm classForm) {
        return null;
    }

    @Override
    public void saveStudents(Long id, IdForm form) {
        final Class schoolClass = classService.getById(id);
        final List<Student> students = studentService.getAllById(form.getIds());

        classService.saveStudents(schoolClass, students);
    }
}
