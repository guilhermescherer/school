package com.personal.school.controller;

import com.personal.school.dto.ClassDTO;
import com.personal.school.dto.ClassDetailsDTO;
import com.personal.school.model.Class;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.personal.school.dto.ClassDTO.toDto;

@RestController
@RequestMapping("/class")
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

}
