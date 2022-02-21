package com.personal.school.converter.impl;

import com.personal.school.converter.Converter;
import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.model.TeachingType;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.school.enums.ConvertMethod.ADD;
import static com.personal.school.enums.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;
import static com.personal.school.utils.PropertyUtils.getNullProperties;

public class ClassConverter implements Converter<ClassForm, Class> {

    private final TeacherService teacherService;
    private final StudentService studentService;

    public ClassConverter(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Override
    public Class convert(ClassForm source) {
        Class target = new Class();
        BeanUtils.copyProperties(source, target);

        populateTeaching(target, source.getTeachingType(), ADD);
        populateTeachers(target, source.getTeachers(), ADD);
        populateStudents(target, source.getStudents(), ADD);

        return target;
    }

    @Override
    public Class convert(Class target, ClassForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));

        populateTeaching(target, source.getTeachingType(), UPDATE);
        populateTeachers(target, source.getTeachers(), UPDATE);
        populateStudents(target, source.getStudents(), UPDATE);

        return target;
    }

    private void populateStudents(Class schoolClass, List<Long> ids, ConvertMethod convertMethod) {
        if(isValidSet(ids, convertMethod)) {
            List<Student> students = studentService.getAllById(ids);
            students.forEach(s -> s.setSchoolClass(schoolClass));
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
