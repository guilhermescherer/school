package com.personal.school.service.impl;

import com.personal.school.converter.StudentConverter;
import com.personal.school.exception.NotFoundException;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import com.personal.school.repository.StudentRepository;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class DefaultStudentService implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentConverter studentConverter;

    @Override
    public List<Student> getAll() {
        final List<Student> students = studentRepository.findAll();
        if(isEmpty(students)) {
            throw new NotFoundException("Not found any student");
        }
        return students;
    }

    @Override
    public List<Student> getAllById(@NotNull List<Long> ids) {
        List<Student> students = studentRepository.findAllById(ids);
        if(ids.size() != students.size()) {
            throw new NotFoundException("Not found all students");
        }
        return students;
    }

    @Override
    public Student getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()) {
            throw new NotFoundException("Not found student");
        }
        return student.get();
    }

    @Override
    public Student save(StudentForm studentForm) {
        Student student = studentConverter.toStudent(studentForm);
        studentRepository.save(student);
        return student;
    }

    @Override
    public void remove(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student update(Student student, StudentForm studentUpdateForm){
        return studentConverter.toStudent(student, studentUpdateForm);
    }
}
