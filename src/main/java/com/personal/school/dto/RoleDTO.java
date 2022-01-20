package com.personal.school.dto;

import com.personal.school.model.Role;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoleDTO {

    private final Long id;
    private final String name;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getAuthority();
    }

    public static List<RoleDTO> toDto(List<Role> roles){
        return roles.stream()
                .map(RoleDTO::new)
                .collect(Collectors.toList());
    }

}
