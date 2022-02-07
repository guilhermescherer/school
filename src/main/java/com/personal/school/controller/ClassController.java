package com.personal.school.controller;

import com.personal.school.dto.ClassDTO;
import com.personal.school.dto.ClassDetailsDTO;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.service.ClassService;
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
    ClassService classService;

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
        Class schoolClass = classService.toClass(classForm);
        classService.save(schoolClass);

        URI uri = uriBuilder.path("/class/{id}").buildAndExpand(schoolClass.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClassDTO(schoolClass));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassDTO> update(@PathVariable Long id, @RequestBody @Valid ClassForm classForm){
        Class schoolClass = classService.update(id, classForm);
        return ResponseEntity.ok(new ClassDTO(schoolClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        classService.remove(id);
        return ResponseEntity.ok().build();
    }

}
