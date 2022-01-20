package com.personal.school.controller;

import com.personal.school.dto.TeacherDetailsDTO;
import com.personal.school.dto.UserDTO;
import com.personal.school.dto.UserDetailsDTO;
import com.personal.school.form.UserForm;
import com.personal.school.model.Teacher;
import com.personal.school.model.User;
import com.personal.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.personal.school.dto.UserDTO.toDto;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> getAll(){
        List<User> users = userService.getAll();
        return toDto(users);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getById(@PathVariable Long id){
        Optional<User> user = userService.getById(id);
        return user.map(value -> ResponseEntity.ok(new UserDetailsDTO(user.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.toUser(userForm);
        userService.save(user);

        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

}