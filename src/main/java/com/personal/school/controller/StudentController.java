package com.personal.school.controller;

import com.personal.school.dto.StudentDTO;
import com.personal.school.facade.StudentFacade;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Student;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.personal.school.dto.StudentDTO.toDto;
import static com.personal.school.utils.SecurityUtils.ROLE_ADMIN;
import static com.personal.school.utils.SecurityUtils.ROLE_USER;

@Api(tags = "Student")
@RestController
@RequestMapping("/student")
@Secured({ROLE_ADMIN, ROLE_USER})
public class StudentController {

    @Autowired
    StudentFacade studentFacade;

    @GetMapping
    public List<StudentDTO> getAll(){
        List<Student> students = studentFacade.getAll();
        return toDto(students);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
        Student student = studentFacade.getById(id);
        return ResponseEntity.ok(new StudentDTO(student));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Valid StudentForm studentForm, UriComponentsBuilder uriBuilder){
        Student student = studentFacade.save(studentForm);

        URI uri = uriBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(new StudentDTO(student));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody @Valid StudentForm studentForm){
        Student student = studentFacade.update(id, studentForm);
        return ResponseEntity.ok(new StudentDTO(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        studentFacade.remove(id);
        return ResponseEntity.ok().build();
    }

}
