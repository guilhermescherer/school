package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.PeopleForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.People;
import com.personal.school.model.Schooling;
import com.personal.school.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.personal.school.enums.ConvertMethod.ADD;
import static com.personal.school.enums.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Component
public class DefaultTeacherConverter implements Converter<TeacherForm, Teacher> {

    @Autowired
    AbstractConverter<PeopleForm, People> peopleConverter;

    @Override
    public Teacher convert(TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.convert(new Teacher(), source, ADD);

        populateSalary(target, source.getSalary());
        populateSchooling(target, source.getSchooling(), ADD);

        return target;
    }

    @Override
    public Teacher convert(Teacher teacher, TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.convert(teacher, source, UPDATE);

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
