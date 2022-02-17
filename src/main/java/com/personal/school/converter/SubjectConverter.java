package com.personal.school.converter;

import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;

public interface SubjectConverter {

    Subject toSubject(SubjectForm subjectForm);

    Subject toSubject(Subject subject, SubjectForm subjectForm);
}
