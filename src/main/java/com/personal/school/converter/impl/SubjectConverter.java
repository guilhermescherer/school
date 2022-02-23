package com.personal.school.converter.impl;

import com.personal.school.converter.Converter;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import org.springframework.beans.BeanUtils;

import static com.personal.school.utils.PropertyUtils.getNullProperties;

public class SubjectConverter implements Converter<SubjectForm, Subject> {

    @Override
    public Subject convert(SubjectForm source) {
        return convert(new Subject(), source);
    }

    @Override
    public Subject convert(Subject target, SubjectForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
        return target;
    }
}
