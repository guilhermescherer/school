package com.personal.school.dto;

import com.personal.school.model.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDTO {

    private final Long id;
    private final String name;
    private final String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public static List<UserDTO> toDto(List<User> users){
        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }
}
