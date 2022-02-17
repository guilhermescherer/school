package com.personal.school.controller;

import com.personal.school.dto.UserDTO;
import com.personal.school.dto.UserDetailsDTO;
import com.personal.school.facade.UserFacade;
import com.personal.school.form.UserForm;
import com.personal.school.form.groups.Add;
import com.personal.school.form.groups.Update;
import com.personal.school.model.User;
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

import static com.personal.school.dto.UserDTO.toDto;
import static com.personal.school.utils.SecurityUtils.ROLE_ADMIN;

@Api(tags = "User")
@RestController
@RequestMapping("/user")
@Secured(ROLE_ADMIN)
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping
    public List<UserDTO> getAll(){
        List<User> users = userFacade.getAll();
        return toDto(users);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getById(@PathVariable Long id){
        User user = userFacade.getById(id);
        return ResponseEntity.ok(new UserDetailsDTO(user));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> add(@RequestBody @Validated({Add.class, Default.class}) UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {
        User user = userFacade.save(userForm);

        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                          @RequestBody @Validated({Update.class, Default.class}) UserForm userForm){
        User user = userFacade.update(id, userForm);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        userFacade.remove(id);
        return ResponseEntity.ok().build();
    }
}
