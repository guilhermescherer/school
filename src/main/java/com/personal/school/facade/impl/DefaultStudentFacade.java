package com.personal.school.facade.impl;

import com.personal.school.converter.Converter;
import com.personal.school.converter.impl.StudentConverter;
import com.personal.school.facade.Facade;
import com.personal.school.facade.StudentFacade;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class DefaultStudentFacade implements StudentFacade {

    private final StudentService studentService;
    private final Converter<StudentForm, Student> studentConverter;

    @Autowired
    public DefaultStudentFacade(StudentService studentService) {
        this.studentService = studentService;
        this.studentConverter = new StudentConverter();
    }

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
        final Student student = studentConverter.convert(studentForm);
        return studentService.save(student);
    }

    @Override
    public void remove(Long id) {
        Student student = studentService.getById(id);
        studentService.remove(student);
    }

    @Override
    public Student update(Long id, StudentForm studentForm) {
        Student student = studentService.getById(id);
        return studentConverter.convert(student, studentForm);
    }
}
