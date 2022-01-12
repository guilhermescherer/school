package com.personal.school.controller;

import com.personal.school.dto.StudentDTO;
import com.personal.school.dto.TeacherDTO;
import com.personal.school.form.StudentForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;
import com.personal.school.repository.ClassRepository;
import com.personal.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.personal.school.dto.StudentDTO.toDto;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassRepository classRepository;

    @GetMapping
    public List<StudentDTO> getAll(){
        List<Student> students = studentRepository.findAll();
        return toDto(students);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        return student.map(value -> ResponseEntity.ok(new StudentDTO(student.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping
//    @Transactional
//    public ResponseEntity<?> add(@RequestBody @Valid StudentForm studentForm, UriComponentsBuilder uriBuilder){
//        Student student = Student.toStudent(studentForm, classRepository);
//        studentRepository.save(student);
//
//        URI uri = uriBuilder.path("/teacher/{id}").buildAndExpand(teacher.getId()).toUri();
//        return ResponseEntity.created(uri).body(new TeacherDTO(teacher));
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
