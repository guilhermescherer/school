package com.personal.school.controller;

import com.personal.school.dto.SubjectDto;
import com.personal.school.form.SubjectForm;
import com.personal.school.model.Subject;
import com.personal.school.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.personal.school.dto.SubjectDto.toDto;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<SubjectDto> getAllSubject(){
        List<Subject> subjects = subjectRepository.findAll();
        return toDto(subjects);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.map(value -> ResponseEntity.ok(new SubjectDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SubjectDto> addSubject(@RequestBody @Valid SubjectForm subjectForm, UriComponentsBuilder uriBuilder){
        Subject subject = Subject.toSubject(subjectForm);
        subjectRepository.save(subject);

        URI uri = uriBuilder.path("/subject/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).body(new SubjectDto(subject));
    }

}
