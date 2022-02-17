package com.personal.school.converter.impl;

import com.personal.school.converter.ClassConverter;
import com.personal.school.converter.ConvertMethod;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.model.TeachingType;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.school.converter.ConvertMethod.ADD;
import static com.personal.school.converter.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Service
public class DefaultClassConverter implements ClassConverter {

    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @Override
    public Class toClass(ClassForm source) {
        Class target = new Class();
        BeanUtils.copyProperties(source, target);

        populateTeaching(target, source.getTeachingType(), ADD);
        populateTeachers(target, source.getTeachers(), ADD);
        populateStudents(target, source.getStudents(), ADD);

        return target;
    }

    @Override
    public Class toClass(Class target, ClassForm source) {
        BeanUtils.copyProperties(source, target);

        populateTeaching(target, source.getTeachingType(), UPDATE);
        populateTeachers(target, source.getTeachers(), UPDATE);
        populateStudents(target, source.getStudents(), UPDATE);

        return target;
    }

    private void populateStudents(Class schoolClass, List<Long> students, ConvertMethod convertMethod) {
        if(isValidSet(students, convertMethod)) {
            schoolClass.setStudents(studentService.getAllById(students));
        }
    }

    private void populateTeachers(Class schoolClass, List<Long> teachers, ConvertMethod convertMethod) {
        if(isValidSet(teachers, convertMethod)) {
            schoolClass.setTeachers(teacherService.getAllById(teachers));
        }
    }

    private void populateTeaching(Class schoolClass, String teachingType, ConvertMethod convertMethod) {
        if(isValidSet(teachingType, convertMethod)) {
            schoolClass.setTeachingType(TeachingType.valueOf(teachingType));
        }
    }
}
