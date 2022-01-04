package com.personal.school.controller;

import com.personal.school.dto.SubjectDTO;
import com.personal.school.exception.NotFoundException;
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

import static com.personal.school.dto.SubjectDTO.toDto;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<SubjectDTO> getAllSubject(){
        List<Subject> subjects = subjectRepository.findAll();
        return toDto(subjects);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.map(value -> ResponseEntity.ok(new SubjectDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody @Valid SubjectForm subjectForm, UriComponentsBuilder uriBuilder){
        Subject subject = Subject.toSubject(subjectForm);
        subjectRepository.save(subject);

        URI uri = uriBuilder.path("/subject/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).body(new SubjectDTO(subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSubject(@PathVariable Long id){

        if(!subjectRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format("Subject with id [%s] does not exist", id));
        }

        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
