package com.personal.school.converter.impl;

import com.personal.school.converter.SubjectConverter;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultSubjectConverter implements SubjectConverter {

    @Autowired
    TeacherService teacherService;

    @Override
    public Subject toSubject(SubjectForm subjectForm) {
        return toSubject(false, null, subjectForm);
    }

    @Override
    public Subject toSubject(Subject subject, SubjectForm subjectForm) {
        return toSubject(true, subject, subjectForm);
    }

    private Subject toSubject(Boolean isUpdate, Subject subject, SubjectForm subjectForm) {
        List<Teacher> teachers = teacherService.getAllByIdThrow(subjectForm.getTeachers());

        if(isUpdate) {
            subject.setName(subjectForm.getName());
            subject.setTeachers(teachers);
        } else {
            subject = new Subject(subjectForm.getName(), teachers);
        }

        return subject;
    }
}
