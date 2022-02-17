package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.PeopleConverter;
import com.personal.school.converter.StudentConverter;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.personal.school.converter.ConvertMethod.ADD;
import static com.personal.school.converter.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Component
public class DefaultStudentConverter implements StudentConverter {

    @Autowired
    PeopleConverter peopleConverter;

    @Override
    public Student toStudent(StudentForm source) {
        Student target = (Student) peopleConverter.toPeople(new Student(), source, ADD);

        populateScholarshipHolder(target, source.getIsScholarshipHolder(), ADD);

        return target;
    }

    @Override
    public Student toStudent(Student student, StudentForm source) {
        Student target = (Student) peopleConverter.toPeople(student, source, UPDATE);

        populateScholarshipHolder(target, source.getIsScholarshipHolder(), UPDATE);

        return target;
    }

    private void populateScholarshipHolder(Student student, Boolean isScholarshipHolder, ConvertMethod convertMethod) {
        if(isValidSet(isScholarshipHolder, convertMethod)) {
            student.setIsScholarshipHolder(isScholarshipHolder);
        }
    }
}
