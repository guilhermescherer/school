package com.personal.school.converter.impl;

import com.personal.school.converter.ClassConverter;
import com.personal.school.converter.ConvertMethod;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.model.TeachingType;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
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
    public Class toClass(ClassForm classForm) {
        Class schoolClass = new Class();
        return getClass(classForm, schoolClass, ADD);
    }

    @Override
    public Class toClass(Class schoolClass, ClassForm classForm) {
        return getClass(classForm, schoolClass, UPDATE);
    }

    private Class getClass(ClassForm classForm, Class schoolClass, ConvertMethod convertMethod) {
        populateName(schoolClass, classForm.getName(), convertMethod);
        populateQualification(schoolClass, classForm.getQualificationNumber(), convertMethod);
        populateTeaching(schoolClass, classForm.getTeachingType(), convertMethod);
        populateTeachers(schoolClass, classForm.getTeachers(), convertMethod);
        populateStudents(schoolClass, classForm.getStudents(), convertMethod);

        return schoolClass;
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

    private void populateQualification(Class schoolClass, Integer qualificationNumber, ConvertMethod convertMethod) {
        if(isValidSet(qualificationNumber, convertMethod)) {
            schoolClass.setQualificationNumber(qualificationNumber);
        }
    }

    private void populateName(Class schoolClass, String name, ConvertMethod convertMethod) {
        if(isValidSet(name, convertMethod)) {
            schoolClass.setName(name);
        }
    }

}
