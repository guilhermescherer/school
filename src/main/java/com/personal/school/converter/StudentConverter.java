package com.personal.school.converter;

import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;

public interface StudentConverter {

    Student toStudent(StudentForm studentForm);

    Student toStudent(Student student, StudentForm studentUpdateForm);
}
