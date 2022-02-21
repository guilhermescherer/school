package com.personal.school.service.impl;

import com.personal.school.enums.UpdateSalaryType;
import com.personal.school.exception.NotFoundException;
import com.personal.school.form.ReajustSalaryForm;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.repository.TeacherRepository;
import com.personal.school.service.SalaryService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class DefaultTeacherService implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public DefaultTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAll() {
        final List<Teacher> teachers = teacherRepository.findAll();
        if(isEmpty(teachers)) {
            throw new NotFoundException("Not found any teacher");
        }
        return teachers;
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
    public Teacher save(Teacher teacher) {
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
    public void removeSubjects(Teacher teacher, List<Subject> subjects) {
        List<Subject> currentSubjects = teacher.getSubjects();
        currentSubjects.removeAll(subjects);
    }

    @Override
    public void updateSalary(Teacher teacher, ReajustSalaryForm reajustSalaryForm) {
        final SalaryService salaryService = new DefaultSalaryService();

        final UpdateSalaryType updateSalaryType = UpdateSalaryType.valueOf(reajustSalaryForm.getUpdateSalaryType());
        final BigDecimal value = new BigDecimal(reajustSalaryForm.getValue());

        final BigDecimal salary = salaryService.updateSalary(teacher.getSalary(), updateSalaryType, value);

        teacher.setSalary(salary);
    }
}
