package com.personal.school.controller;

import com.personal.school.dto.ClassDTO;
import com.personal.school.model.Class;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
