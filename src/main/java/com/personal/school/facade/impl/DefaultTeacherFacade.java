package com.personal.school.facade.impl;

import com.personal.school.converter.Converter;
import com.personal.school.converter.impl.TeacherConverter;
import com.personal.school.facade.Facade;
import com.personal.school.facade.TeacherFacade;
import com.personal.school.form.UpdateSalaryForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.service.SubjectService;
import com.personal.school.service.TeacherService;
import com.personal.school.validator.Validators;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class DefaultTeacherFacade implements TeacherFacade {

    private final TeacherService teacherService;
    private final SubjectService subjectService;

    private final Converter<TeacherForm, Teacher> teacherConverter;

    @Autowired
    public DefaultTeacherFacade(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.teacherConverter = new TeacherConverter();
    }

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
        final Teacher teacher = teacherConverter.convert(teacherForm);
        return teacherService.save(teacher);
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
        return teacherConverter.convert(teacher, teacherForm);
    }

    @Override
    public void updateSalary(Long id, UpdateSalaryForm updateSalaryForm) {
        Teacher teacher = teacherService.getById(id);

        Validators.getUpdateSalaryValidator().check(teacher, updateSalaryForm);

        teacherService.updateSalary(teacher, updateSalaryForm);
    }
}
