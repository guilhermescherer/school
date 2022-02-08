package com.personal.school.converter.impl;

import com.personal.school.converter.StudentConverter;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

@Component
public class DefaultStudentConverter implements StudentConverter {

    @Autowired
    ClassService classService;

    @Override
    public Student toStudent(StudentForm studentForm) {
        return toStudent(new Student(), studentForm);
    }

    @Override
    public Student toStudent(Student student, StudentForm studentForm) {
        Class schoolClass = classService.getByIdThrow(studentForm.getSchoolClass());
        LocalDate birthDate = LocalDate.parse(studentForm.getBirthDate(), getDefaultDateFormatter());
        String cpf = getCpfUnformat(studentForm.getCpf());

        student.setName(studentForm.getName());
        student.setEmail(studentForm.getEmail());
        student.setTelephone(studentForm.getTelephone());
        student.setCpf(cpf);
        student.setBirthDate(birthDate);
        student.setIsScholarshipHolder(studentForm.getIsScholarshipHolder());
        student.setSchoolClass(schoolClass);

        return student;
    }
}
