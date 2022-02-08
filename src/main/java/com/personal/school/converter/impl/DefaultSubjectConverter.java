package com.personal.school.converter.impl;

import com.personal.school.converter.SubjectConverter;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultSubjectConverter implements SubjectConverter {

    @Autowired
    TeacherService teacherService;

    @Override
    public Subject toSubject(SubjectForm subjectForm) {
        return toSubject(new Subject(), subjectForm);
    }

    @Override
    public Subject toSubject(Subject subject, SubjectForm subjectForm) {
        List<Teacher> teachers = teacherService.getAllByIdThrow(subjectForm.getTeachers());

        subject.setName(subjectForm.getName());
        subject.setTeachers(teachers);

        return subject;
    }
}
