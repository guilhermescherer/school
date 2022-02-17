package com.personal.school.converter.impl;

import com.personal.school.converter.SubjectConverter;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import static com.personal.school.utils.PropertyUtils.getNullProperties;

@Component
public class DefaultSubjectConverter implements SubjectConverter {

    @Override
    public Subject toSubject(SubjectForm source) {
        Subject target = new Subject();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    @Override
    public Subject toSubject(Subject target, SubjectForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
        return target;
    }
}
