package com.personal.school.service.impl;

import com.personal.school.converter.TeacherConverter;
import com.personal.school.enums.UpdateSalaryType;
import com.personal.school.exception.NotFoundException;
import com.personal.school.form.ReajustSalaryForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.repository.TeacherRepository;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultTeacherService implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherConverter teacherConverter;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(!teacher.isPresent()){
            throw new NotFoundException("Not found teacher");
        }
        return teacher.get();
    }

    @Override
    public List<Teacher> getAllById(@NotNull List<Long> ids) {
        List<Teacher> teachers = teacherRepository.findAllById(ids);
        if(ids.size() != teachers.size()) {
            throw new NotFoundException("Not found all teachers");
        }
        return teachers;
    }

    @Override
    public Teacher save(TeacherForm teacherForm) {
        Teacher teacher = teacherConverter.toTeacher(teacherForm);
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public void saveSubjects(Teacher teacher, List<Subject> subjects) {
        List<Subject> currentSubjects = teacher.getSubjects();
        currentSubjects.addAll(subjects);
    }

    @Override
    public void remove(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher update(Teacher teacher, TeacherForm teacherForm) {
        return teacherConverter.toTeacher(teacher, teacherForm);
    }

    @Override
    public void updateSalary(Teacher teacher, ReajustSalaryForm reajustSalaryForm) {
        UpdateSalaryType updateSalaryType = UpdateSalaryType.valueOf(reajustSalaryForm.getUpdateSalaryType());
        teacher.updateSalary(reajustSalaryForm.getValue(), updateSalaryType);
    }

    @Override
    public void removeSubjects(Teacher teacher, List<Subject> subjects) {
        List<Subject> currentSubjects = teacher.getSubjects();
        currentSubjects.removeAll(subjects);
    }
}
