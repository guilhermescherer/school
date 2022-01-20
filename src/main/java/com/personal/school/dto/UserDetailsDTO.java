package com.personal.school.dto;

import com.personal.school.model.User;
import lombok.Getter;

import java.util.List;

import static com.personal.school.dto.RoleDTO.toDto;

@Getter
public class UserDetailsDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final List<RoleDTO> roles;

    public UserDetailsDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = toDto(user.getRoles());
    }
}
