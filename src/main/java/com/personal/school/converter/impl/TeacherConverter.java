package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.PeopleForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Address;
import com.personal.school.model.People;
import com.personal.school.model.Schooling;
import com.personal.school.model.Teacher;

import java.math.BigDecimal;

import static com.personal.school.enums.ConvertMethod.ADD;
import static com.personal.school.enums.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

public class TeacherConverter implements Converter<TeacherForm, Teacher> {

    private final AbstractConverter<PeopleForm, People> peopleConverter;
    private final Converter<PeopleForm, Address> addressConverter;

    public TeacherConverter() {
        this.peopleConverter = new PeopleConverter();
        this.addressConverter = new AddressConverter();
    }

    @Override
    public Teacher convert(TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.convert(new Teacher(), source, ADD);
        Address address = addressConverter.convert(source);

        populateSalary(target, source.getSalary());
        populateSchooling(target, source.getSchooling(), ADD);
        populateAddress(target, address, ADD);

        return target;
    }

    @Override
    public Teacher convert(Teacher teacher, TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.convert(teacher, source, UPDATE);
        Address address = addressConverter.convert(source);

        populateSchooling(target, source.getSchooling(), UPDATE);
        populateAddress(target, address, UPDATE);

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

    private void populateAddress(Teacher teacher, Address address, ConvertMethod convertMethod) {
        if(isValidSet(address, convertMethod)) {
            teacher.setAddress(address);
        }
    }
}
