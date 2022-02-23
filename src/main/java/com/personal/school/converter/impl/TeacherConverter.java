package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.form.PeopleForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Address;
import com.personal.school.model.People;
import com.personal.school.model.Schooling;
import com.personal.school.model.Teacher;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.personal.school.utils.SetterUtils.setter;

public class TeacherConverter implements Converter<TeacherForm, Teacher> {

    private final AbstractConverter<PeopleForm, People> peopleConverter;
    private final Converter<PeopleForm, Address> addressConverter;

    public TeacherConverter() {
        this.peopleConverter = new PeopleConverter();
        this.addressConverter = new AddressConverter();
    }

    @Override
    public Teacher convert(TeacherForm source) {
        return convert(new Teacher(), source);
    }

    @Override
    public Teacher convert(Teacher teacher, TeacherForm source) {
        Teacher target = (Teacher) peopleConverter.convert(teacher, source);

        final Function<Double, BigDecimal> salary = BigDecimal::valueOf;
        final Function<String, Schooling> schooling = Schooling::valueOf;
        final Function<String, Address> address = a -> addressConverter.convert(source);

        setter(target::setSalary, source.getSalary(), salary);
        setter(target::setSchooling, source.getSchooling(), schooling);
        setter(target::setAddress, source.getAddress(), address);

        return target;
    }
}
