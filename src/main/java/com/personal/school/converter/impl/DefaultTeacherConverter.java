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
    public Teacher toTeacher(TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.toPeople(new Teacher(), source, ADD);

        populateSalary(target, source.getSalary());
        populateSchooling(target, source.getSchooling(), ADD);

        return target;
    }

    @Override
    public Teacher toTeacher(Teacher teacher, TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.toPeople(teacher, source, UPDATE);

        populateSchooling(target, source.getSchooling(), UPDATE);

        return target;
    }

    private void populateSchooling(Teacher teacher, String schooling, ConvertMethod convertMethod) {
        if(isValidSet(schooling, convertMethod)) {
            teacher.setSchooling(Schooling.valueOf(schooling));
        }
    }

    private void populateSalary(Teacher teacher, Double salary) {
        teacher.setSalary(BigDecimal.valueOf(salary));
    }
}
