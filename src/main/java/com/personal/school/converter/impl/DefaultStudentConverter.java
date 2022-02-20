package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.PeopleForm;
import com.personal.school.form.StudentForm;
import com.personal.school.model.People;
import com.personal.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.personal.school.enums.ConvertMethod.ADD;
import static com.personal.school.enums.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Component
public class DefaultStudentConverter implements Converter<StudentForm, Student> {

    @Autowired
    AbstractConverter<PeopleForm, People> peopleConverter;

    @Override
    public Student convert(StudentForm source) {
        Student target = (Student) peopleConverter.convert(new Student(), source, ADD);

        populateScholarshipHolder(target, source.getIsScholarshipHolder(), ADD);

        return target;
    }

    @Override
    public Student convert(Student student, StudentForm source) {
        Student target = (Student) peopleConverter.convert(student, source, UPDATE);

        populateScholarshipHolder(target, source.getIsScholarshipHolder(), UPDATE);

        return target;
    }

    private void populateScholarshipHolder(Student student, Boolean isScholarshipHolder, ConvertMethod convertMethod) {
        if(isValidSet(isScholarshipHolder, convertMethod)) {
            student.setIsScholarshipHolder(isScholarshipHolder);
        }
    }
}
