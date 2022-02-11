package com.personal.school.service.impl;

import com.personal.school.converter.StudentConverter;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import com.personal.school.repository.StudentRepository;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class DefaultStudentService implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentConverter studentConverter;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllByIdThrow(List<Long> ids) {
        if(isNull(ids)) return EMPTY_LIST;

        List<Student> students = studentRepository.findAllById(ids);
        if(ids.size() != students.size()) {
            throw new EmptyResultDataAccessException("Not found all students", ids.size());
        }

        return students;
    }

    @Override
    public Student getByIdThrow(Long id) {
        Optional<Student> student = getById(id);
        if(!student.isPresent()) {
            throw new EmptyResultDataAccessException("Not found student", toIntExact(id));
        }
        return student.get();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(StudentForm studentForm) {
        Student student = studentConverter.toStudent(studentForm);
        studentRepository.save(student);
        return student;
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Long id, StudentForm studentUpdateForm){
        Student student = getByIdThrow(id);
        return studentConverter.toStudent(student, studentUpdateForm);
    }
}
