package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.PeopleForm;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Address;
import com.personal.school.model.People;
import com.personal.school.model.Student;
import org.springframework.stereotype.Component;

import static com.personal.school.enums.ConvertMethod.ADD;
import static com.personal.school.enums.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Component
public class StudentConverter implements Converter<StudentForm, Student> {

    private final AbstractConverter<PeopleForm, People> peopleConverter;
    private final Converter<PeopleForm, Address> addressConverter;

    public StudentConverter() {
        this.peopleConverter = new PeopleConverter();
        this.addressConverter = new AddressConverter();
    }

    @Override
    public Student convert(StudentForm source) {
        Student target = (Student) peopleConverter.convert(new Student(), source, ADD);
        Address address = addressConverter.convert(source);

        populateScholarshipHolder(target, source.getIsScholarshipHolder(), ADD);
        populateAddress(target, address, ADD);

        return target;
    }

    @Override
    public Student convert(Student student, StudentForm source) {
        Student target = (Student) peopleConverter.convert(student, source, UPDATE);
        Address address = addressConverter.convert(source);

        populateScholarshipHolder(target, source.getIsScholarshipHolder(), UPDATE);
        populateAddress(target, address, UPDATE);

        return target;
    }

    private void populateScholarshipHolder(Student student, Boolean isScholarshipHolder, ConvertMethod convertMethod) {
        if(isValidSet(isScholarshipHolder, convertMethod)) {
            student.setIsScholarshipHolder(isScholarshipHolder);
        }
    }

    private void populateAddress(Student student, Address address, ConvertMethod convertMethod) {
        if(isValidSet(address, convertMethod)) {
            student.setAddress(address);
        }
    }
}
