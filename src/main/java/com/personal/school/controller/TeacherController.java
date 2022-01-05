package com.personal.school.controller;

import com.personal.school.dto.TeacherDTO;
import com.personal.school.form.SubjectForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Teacher;
import com.personal.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static com.personal.school.dto.TeacherDTO.toDto;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public List<TeacherDTO> getAll(){
        List<Teacher> teachers = teacherRepository.findAll();
        return toDto(teachers);
    }

//    public ResponseEntity<TeacherDTO> add(@RequestBody @Valid TeacherForm teacherForm, UriComponentsBuilder uriBuilder){
//
//
//
//    }

}
