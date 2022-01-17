package com.personal.school.controller;

import com.personal.school.dto.StudentDTO;
import com.personal.school.dto.TeacherDTO;
import com.personal.school.form.StudentForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;
import com.personal.school.repository.ClassRepository;
import com.personal.school.repository.StudentRepository;
import com.personal.school.service.ClassService;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    StudentService studentService;
    @Autowired
    ClassService classService;

    @GetMapping
    public List<StudentDTO> getAll(){
        List<Student> students = studentService.getAll();
        return toDto(students);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
        Optional<Student> student = studentService.getById(id);
        return student.map(value -> ResponseEntity.ok(new StudentDTO(student.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Valid StudentForm studentForm, UriComponentsBuilder uriBuilder){
        Student student = studentService.toStudent(studentForm, classService);
        studentService.save(student);

        URI uri = uriBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(new StudentDTO(student));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody @Valid StudentForm studentForm){
        Student student = studentService.update(id, studentForm, classService);
        return ResponseEntity.ok(new StudentDTO(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }

}
