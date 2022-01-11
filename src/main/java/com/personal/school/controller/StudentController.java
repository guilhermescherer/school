package com.personal.school.controller;

import com.personal.school.dto.StudentDTO;
import com.personal.school.model.Student;
import com.personal.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.personal.school.dto.StudentDTO.toDto;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public List<StudentDTO> getAll(){
        List<Student> students = studentRepository.findAll();
        return toDto(students);
    }

}
