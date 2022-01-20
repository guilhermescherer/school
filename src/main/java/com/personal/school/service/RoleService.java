package com.personal.school.service;

import com.personal.school.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllByIdThrow(List<Long> ids);

}
