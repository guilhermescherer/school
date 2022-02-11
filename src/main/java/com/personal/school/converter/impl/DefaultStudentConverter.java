package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.PeopleConverter;
import com.personal.school.converter.StudentConverter;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

@Component
public class DefaultStudentConverter implements StudentConverter {

    @Autowired
    PeopleConverter peopleConverter;

    @Override
    public Student toStudent(StudentForm studentForm) {
        LocalDate birthDate = LocalDate.parse(studentForm.getBirthDate(), getDefaultDateFormatter());
        String cpf = getCpfUnformat(studentForm.getCpf());

        return new Student(studentForm.getName(), studentForm.getEmail(), studentForm.getTelephone(), cpf,
                birthDate, studentForm.getIsScholarshipHolder());
    }

    @Override
    public Student toStudent(Student student, StudentForm studentUpdateForm) {
        Student studentPopulated = (Student) peopleConverter.toPeople(student, studentUpdateForm, ConvertMethod.UPDATE);

        populateScholarshipHolder(studentPopulated, studentUpdateForm);

        return studentPopulated;
    }

    private void populateScholarshipHolder(Student student, StudentForm studentUpdateForm) {
        Boolean isScholarshipHolder = studentUpdateForm.getIsScholarshipHolder();
        if(isScholarshipHolder != null) {
            student.setIsScholarshipHolder(isScholarshipHolder);
        }
    }
}
