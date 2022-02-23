package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.form.PeopleForm;
import com.personal.school.form.StudentForm;
import com.personal.school.model.People;
import com.personal.school.model.Student;

import static com.personal.school.utils.SetterUtils.setter;

public class StudentConverter implements Converter<StudentForm, Student> {

    private final AbstractConverter<PeopleForm, People> peopleConverter;

    public StudentConverter() {
        this.peopleConverter = new PeopleConverter();
    }

    @Override
    public Student convert(StudentForm source) {
        return convert(new Student(), source);
    }

    @Override
    public Student convert(Student student, StudentForm source) {
        Student target = (Student) peopleConverter.convert(student, source);

        setter(target::setIsScholarshipHolder, source.getIsScholarshipHolder());

        return target;
    }
}
