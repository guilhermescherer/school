package com.personal.school.converter;

import com.personal.school.form.TeacherForm;
import com.personal.school.model.Teacher;

public interface TeacherConverter {

    Teacher toTeacher(TeacherForm teacherForm);

    Teacher toTeacher(Teacher teacher, TeacherForm teacherForm);
}
