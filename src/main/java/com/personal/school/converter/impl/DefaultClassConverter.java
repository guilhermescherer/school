package com.personal.school.converter.impl;

import com.personal.school.converter.ClassConverter;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;
import com.personal.school.model.TeachingType;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultClassConverter implements ClassConverter {

    @Autowired @Lazy
    TeacherService teacherService;
    @Autowired @Lazy
    StudentService studentService;

    @Override
    public Class toClass(ClassForm classForm) {
        return toClass(new Class(), classForm);
    }

    @Override
    public Class toClass(Class schoolClass, ClassForm classForm) {
        TeachingType teachingType = TeachingType.valueOf(classForm.getTeachingType());
        List<Teacher> teachers = teacherService.getAllByIdThrow(classForm.getTeachers());
        List<Student> students = studentService.getAllByIdThrow(classForm.getStudents());

        schoolClass.setName(classForm.getName());
        schoolClass.setQualificationNumber(classForm.getQualificationNumber());
        schoolClass.setTeachingType(teachingType);
        schoolClass.setTeachers(teachers);
        schoolClass.setStudents(students);

        return schoolClass;
    }
}
