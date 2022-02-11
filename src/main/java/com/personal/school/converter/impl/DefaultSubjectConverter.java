package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.SubjectConverter;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import org.springframework.stereotype.Component;

import static com.personal.school.converter.ConvertMethod.ADD;
import static com.personal.school.converter.ConvertMethod.UPDATE;
import static com.personal.school.utils.ConverterUtils.isValidSet;

@Component
public class DefaultSubjectConverter implements SubjectConverter {

    @Override
    public Subject toSubject(SubjectForm subjectForm) {
        Subject subject = new Subject();

        populateName(subject, subjectForm.getName(), ADD);

        return subject;
    }

    @Override
    public Subject toSubject(Subject subject, SubjectForm subjectForm) {
        populateName(subject, subjectForm.getName(), UPDATE);

        return subject;
    }

    private void populateName(Subject subject, String name, ConvertMethod convertMethod) {
        if(isValidSet(name, convertMethod)) {
            subject.setName(name);
        }
    }
}
