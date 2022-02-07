package com.personal.school.service.impl;

import com.personal.school.converter.TeacherConverter;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Teacher;
import com.personal.school.repository.TeacherRepository;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

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
    public List<Teacher> getAllByIdThrow(List<Long> ids) {
        if(isNull(ids)) return EMPTY_LIST;

        List<Teacher> teachers = teacherRepository.findAllById(ids);
        if(ids.size() != teachers.size()) {
            throw new EmptyResultDataAccessException("Not found teacher", ids.size());
        }
        return teachers;
    }

    @Override
    public Teacher getByIdThrow(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(!teacher.isPresent()){
            throw new EmptyResultDataAccessException("Not found teacher", toIntExact(id));
        }
        return teacher.get();
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher save(TeacherForm teacherForm) {
        Teacher teacher = teacherConverter.toTeacher(teacherForm);
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public void remove(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher update(Long id, TeacherForm teacherForm) {
        Teacher teacher = getByIdThrow(id);
        return teacherConverter.toTeacher(teacher, teacherForm);
    }
}
