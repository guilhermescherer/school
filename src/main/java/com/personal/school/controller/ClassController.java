package com.personal.school.controller;

import com.personal.school.dto.ClassDTO;
import com.personal.school.dto.ClassDetailsDTO;
import com.personal.school.dto.StudentDTO;
import com.personal.school.form.ClassForm;
import com.personal.school.form.StudentForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.service.ClassService;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.personal.school.dto.ClassDTO.toDto;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<ClassDTO> getAll(){
        List<Class> classes = classService.getAll();
        return toDto(classes);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ClassDetailsDTO> getById(@PathVariable Long id){
        Optional<Class> schoolClass = classService.getById(id);
        return schoolClass.map(value -> ResponseEntity.ok(new ClassDetailsDTO(schoolClass.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Valid ClassForm classForm, UriComponentsBuilder uriBuilder){
        Class schoolClass = classService.toClass(classForm, teacherService, studentService);
        classService.save(schoolClass);

        URI uri = uriBuilder.path("/class/{id}").buildAndExpand(schoolClass.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClassDTO(schoolClass));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassDTO> update(@PathVariable Long id, @RequestBody @Valid ClassForm classForm){
        Class schoolClass = classService.update(id, classForm, teacherService, studentService);
        return ResponseEntity.ok(new ClassDTO(schoolClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        classService.remove(id);
        return ResponseEntity.ok().build();
    }

}