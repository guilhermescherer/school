package com.personal.school.controller;

import com.personal.school.dto.TeacherDTO;
import com.personal.school.dto.TeacherDetailsDTO;
import com.personal.school.facade.TeacherFacade;
import com.personal.school.form.ReajustSalaryForm;
import com.personal.school.form.TeacherForm;
import com.personal.school.form.groups.Add;
import com.personal.school.form.groups.Update;
import com.personal.school.model.Teacher;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.groups.Default;
import java.net.URI;
import java.util.List;

import static com.personal.school.dto.TeacherDTO.toDto;
import static com.personal.school.utils.SecurityUtils.ROLE_ADMIN;
import static com.personal.school.utils.SecurityUtils.ROLE_USER;

@Api(tags = "Teacher")
@RestController
@RequestMapping("/teacher")
@Secured({ROLE_ADMIN, ROLE_USER})
public class TeacherController {

    @Autowired
    TeacherFacade teacherFacade;

    @GetMapping
    public List<TeacherDTO> getAll(){
        List<Teacher> teachers = teacherFacade.getAll();
        return toDto(teachers);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<TeacherDetailsDTO> getById(@PathVariable Long id){
        Teacher teacher = teacherFacade.getById(id);
        return ResponseEntity.ok(new TeacherDetailsDTO(teacher));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Validated({Add.class, Default.class}) TeacherForm teacherForm,
                                 UriComponentsBuilder uriBuilder){
        Teacher teacher = teacherFacade.save(teacherForm);

        URI uri = uriBuilder.path("/teacher/{id}").buildAndExpand(teacher.getId()).toUri();
        return ResponseEntity.created(uri).body(new TeacherDTO(teacher));
    }

    @PostMapping("{idTeacher}/subjects")
    @Transactional
    public ResponseEntity<?> addSubjects(@PathVariable Long idTeacher, @RequestBody List<Long> idsSubjects) {
        teacherFacade.saveSubjects(idTeacher, idsSubjects);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TeacherDTO> update(@PathVariable Long id,
                                             @RequestBody @Validated({Update.class, Default.class}) TeacherForm teacherForm){
        Teacher teacher = teacherFacade.update(id, teacherForm);
        return ResponseEntity.ok(new TeacherDTO(teacher));
    }

    @PutMapping("{id}/salary")
    @Transactional
    public ResponseEntity<?> updateSalary(@PathVariable Long id, @RequestBody @Validated ReajustSalaryForm reajustSalaryForm) {
        teacherFacade.updateSalary(id, reajustSalaryForm);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        teacherFacade.remove(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}/subjects")
    @Transactional
    public ResponseEntity<?> removeSubjects(@PathVariable Long idTeacher, @RequestBody List<Long> idsSubjects) {
        teacherFacade.removeSubjects(idTeacher, idsSubjects);
        return ResponseEntity.ok().build();
    }
}
