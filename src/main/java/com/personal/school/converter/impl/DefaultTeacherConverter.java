package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.PeopleConverter;
import com.personal.school.converter.TeacherConverter;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Schooling;
import com.personal.school.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

import static com.personal.school.converter.ConvertMethod.ADD;
import static com.personal.school.converter.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Component
public class DefaultTeacherConverter implements TeacherConverter {

    @Autowired
    PeopleConverter peopleConverter;

    @Override
    public Teacher toTeacher(TeacherForm teacherForm) {
        Teacher teacher = (Teacher) peopleConverter.toPeople(new Teacher(), teacherForm, ADD);

        teacher.setSalary(BigDecimal.valueOf(teacherForm.getSalary()));
        populateSchooling(teacher, teacherForm.getSchooling(), ADD);

        return teacher;
    }

    @Override
    public Teacher toTeacher(Teacher teacher, TeacherForm teacherForm) {
        Teacher teacherPopulated = (Teacher) peopleConverter.toPeople(teacher, teacherForm, UPDATE);

        populateSchooling(teacherPopulated, teacherForm.getSchooling(), UPDATE);

        return teacherPopulated;
    }

    private void populateSchooling(Teacher teacher, String schooling, ConvertMethod convertMethod) {
        if(isValidSet(schooling, convertMethod)) {
            teacher.setSchooling(Schooling.valueOf(schooling));
        }
    }
}
