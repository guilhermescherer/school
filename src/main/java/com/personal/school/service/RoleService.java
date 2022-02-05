package com.personal.school.service;

import com.personal.school.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllByIdThrow(List<Long> ids);

    Optional<Role> getByName(String nameRole);

    void save(Role role);
}
