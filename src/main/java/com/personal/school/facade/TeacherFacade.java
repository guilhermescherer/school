package com.personal.school.facade;

import com.personal.school.form.ReajustSalaryForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Teacher;

import java.util.List;

public interface TeacherFacade {

    List<Teacher> getAll();

    Teacher getById(Long id);

    Teacher save(TeacherForm teacherForm);

    void saveSubjects(Long idTeacher, List<Long> idsSubjects);

    void remove(Long id);

    void removeSubjects(Long idTeacher, List<Long> idsSubjects);

    Teacher update(Long id, TeacherForm teacherForm);

    void updateSalary(Long id, ReajustSalaryForm reajustSalaryForm);
}
