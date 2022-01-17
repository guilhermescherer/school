package com.personal.school.controller;

import com.personal.school.dto.StudentDTO;
import com.personal.school.dto.SubjectDTO;
import com.personal.school.dto.SubjectDetailsDTO;
import com.personal.school.form.StudentForm;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Student;
import com.personal.school.model.Subject;
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

import static com.personal.school.dto.SubjectDTO.toDto;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    TeacherService teacherService;

    @GetMapping
    public List<SubjectDTO> getAll(){
        return toDto(subjectService.getAll());
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<SubjectDetailsDTO> getById(@PathVariable Long id){
        Optional<Subject> subject = subjectService.getById(id);
        return subject.map(value -> ResponseEntity.ok(new SubjectDetailsDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SubjectDTO> add(@RequestBody @Valid SubjectForm subjectForm, UriComponentsBuilder uriBuilder){
        Subject subject = subjectService.toSubject(subjectForm, teacherService);
        subjectService.save(subject);

        URI uri = uriBuilder.path("/subject/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).body(new SubjectDTO(subject));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SubjectDTO> update(@PathVariable Long id, @RequestBody @Valid SubjectForm subjectForm){
        Subject subject = subjectService.update(id, subjectForm);
        return ResponseEntity.ok(new SubjectDTO(subject));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        subjectService.remove(id);
        return ResponseEntity.ok().build();
    }

}
