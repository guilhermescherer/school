package com.personal.school.controller;

import com.personal.school.dto.ClassDTO;
import com.personal.school.dto.ClassDetailsDTO;
import com.personal.school.facade.ClassFacade;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
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
import java.util.Optional;

import static com.personal.school.dto.ClassDTO.toDto;
import static com.personal.school.utils.SecurityUtils.ROLE_ADMIN;
import static com.personal.school.utils.SecurityUtils.ROLE_USER;

@Api(tags = "Class")
@RestController
@RequestMapping("/class")
@Secured({ROLE_ADMIN, ROLE_USER})
public class ClassController {

    @Autowired
    ClassFacade classFacade;

    @GetMapping
    public List<ClassDTO> getAll(){
        List<Class> classes = classFacade.getAll();
        return toDto(classes);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ClassDetailsDTO> getById(@PathVariable Long id){
        Class schoolClass = classFacade.getById(id);
        return ResponseEntity.ok(new ClassDetailsDTO(schoolClass));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Valid ClassForm classForm, UriComponentsBuilder uriBuilder){
        Class schoolClass = classFacade.save(classForm);

        URI uri = uriBuilder.path("/class/{id}").buildAndExpand(schoolClass.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClassDTO(schoolClass));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassDTO> update(@PathVariable Long id, @RequestBody @Valid ClassForm classForm){
        Class schoolClass = classFacade.update(id, classForm);
        return ResponseEntity.ok(new ClassDTO(schoolClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        classFacade.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/students")
    @Transactional
    public ResponseEntity<?> addStudents(@PathVariable Long id, @RequestBody List<Long> ids){
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/teachers")
    @Transactional
    public ResponseEntity<?> addTeachers(@PathVariable Long id, @RequestBody List<Long> ids){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id/students")
    @Transactional
    public ResponseEntity<?> deleteStudents(@PathVariable Long id, @RequestBody List<Long> ids){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id/teachers")
    @Transactional
    public ResponseEntity<?> deleteTeachers(@PathVariable Long id, @RequestBody List<Long> ids){
        return ResponseEntity.ok().build();
    }
}
