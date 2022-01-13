package com.personal.school.controller;

import com.personal.school.dto.TeacherDTO;
import com.personal.school.dto.TeacherDetailsDTO;
import com.personal.school.form.TeacherForm;
import com.personal.school.model.Teacher;
import com.personal.school.repository.ClassRepository;
import com.personal.school.repository.SubjectRepository;
import com.personal.school.repository.TeacherRepository;
import com.personal.school.service.ClassService;
import com.personal.school.service.SubjectService;
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

import static com.personal.school.dto.TeacherDTO.toDto;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;
    @Autowired
    SubjectService subjectService;

    @GetMapping
    public List<TeacherDTO> getAll(){
        List<Teacher> teachers = teacherService.getAll();
        return toDto(teachers);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<TeacherDetailsDTO> getById(@PathVariable Long id){
        Optional<Teacher> teacher = teacherService.getById(id);
        return teacher.map(value -> ResponseEntity.ok(new TeacherDetailsDTO(teacher.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Valid TeacherForm teacherForm, UriComponentsBuilder uriBuilder){
        Teacher teacher = Teacher.toTeacher(teacherForm, subjectService, classService);
        teacherService.save(teacher);

        URI uri = uriBuilder.path("/teacher/{id}").buildAndExpand(teacher.getId()).toUri();
        return ResponseEntity.created(uri).body(new TeacherDTO(teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        teacherService.remove(id);
        return ResponseEntity.ok().build();
    }

}
