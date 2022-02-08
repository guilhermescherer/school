package com.personal.school.converter.impl;

import com.personal.school.converter.TeacherConverter;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Class;
import com.personal.school.model.Schooling;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.service.ClassService;
import com.personal.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

@Component
public class DefaultTeacherConverter implements TeacherConverter {

    @Autowired
    ClassService classService;
    @Autowired
    @Lazy
    SubjectService subjectService;

    @Override
    public Teacher toTeacher(TeacherForm teacherForm) {
        return toTeacher(new Teacher(), teacherForm);
    }

    @Override
    public Teacher toTeacher(Teacher teacher, TeacherForm teacherForm) {
        List<Subject> subjects = subjectService.getAllByIdThrow(teacherForm.getSubjects());
        List<Class> classes = classService.getAllByIdThrow(teacherForm.getClasses());

        LocalDate birthDate = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());
        Schooling schooling = Schooling.valueOf(teacherForm.getSchooling());
        String cpf = getCpfUnformat(teacherForm.getCpf());
        BigDecimal salary = BigDecimal.valueOf(teacherForm.getSalary());

        teacher.setName(teacherForm.getName());
        teacher.setEmail(teacherForm.getEmail());
        teacher.setTelephone(teacherForm.getTelephone());
        teacher.setCpf(cpf);
        teacher.setBirthDate(birthDate);
        teacher.setSalary(salary);
        teacher.setSchooling(schooling);
        teacher.setSubjects(subjects);
        teacher.setClasses(classes);

        return teacher;
    }
}
