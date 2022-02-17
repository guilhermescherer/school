package com.personal.school.facade.impl;

import com.personal.school.facade.StudentFacade;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultStudentFacade implements StudentFacade {

    @Autowired
    StudentService studentService;

    @Override
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @Override
    public List<Student> getAllById(List<Long> students) {
        return studentService.getAllById(students);
    }

    @Override
    public Student getById(Long id) {
        return studentService.getById(id);
    }

    @Override
    public Student save(StudentForm studentForm) {
        return studentService.save(studentForm);
    }

    @Override
    public void remove(Long id) {
        Student student = studentService.getById(id);
        studentService.remove(student);
    }

    @Override
    public Student update(Long id, StudentForm studentUpdateForm) {
        Student student = studentService.getById(id);
        return studentService.update(student, studentUpdateForm);
    }
}
