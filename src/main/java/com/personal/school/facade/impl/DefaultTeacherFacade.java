package com.personal.school.facade.impl;

import com.personal.school.facade.Facade;
import com.personal.school.facade.TeacherFacade;
import com.personal.school.form.ReajustSalaryForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.service.SubjectService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class DefaultTeacherFacade implements TeacherFacade {

    @Autowired
    TeacherService teacherService;
    @Autowired
    SubjectService subjectService;

    @Override
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherService.getById(id);
    }

    @Override
    public Teacher save(TeacherForm teacherForm) {
        return teacherService.save(teacherForm);
    }

    @Override
    public void saveSubjects(Long idTeacher, List<Long> idsSubjects) {
        Teacher teacher = teacherService.getById(idTeacher);
        List<Subject> subjects = subjectService.getAllById(idsSubjects);

        teacherService.saveSubjects(teacher, subjects);
    }

    @Override
    public void remove(Long id) {
        Teacher teacher = teacherService.getById(id);
        teacherService.remove(teacher);
    }

    @Override
    public void removeSubjects(Long idTeacher, List<Long> idsSubjects) {
        Teacher teacher = teacherService.getById(idTeacher);
        List<Subject> subjects = subjectService.getAllById(idsSubjects);

        teacherService.removeSubjects(teacher, subjects);
    }

    @Override
    public Teacher update(Long id, TeacherForm teacherForm) {
        Teacher teacher = teacherService.getById(id);
        return teacherService.update(teacher, teacherForm);
    }

    @Override
    public void updateSalary(Long id, ReajustSalaryForm reajustSalaryForm) {
        Teacher teacher = teacherService.getById(id);
        teacherService.updateSalary(teacher, reajustSalaryForm);
    }
}
