package com.personal.school.service.impl;

import com.personal.school.model.Teacher;
import com.personal.school.repository.TeacherRepository;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getAllById(List<Long> ids) {
        List<Teacher> teachers = teacherRepository.findAllById(ids);
        if(ids.size() != teachers.size()) {
            throw new EmptyResultDataAccessException("Not all subjects found", ids.size());
        }
        return teachers;
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void remove(Long id) {
        teacherRepository.deleteById(id);
    }
}
