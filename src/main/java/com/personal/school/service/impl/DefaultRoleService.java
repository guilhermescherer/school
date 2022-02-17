package com.personal.school.service.impl;

import com.personal.school.model.Role;
import com.personal.school.repository.RoleRepository;
import com.personal.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class DefaultRoleService implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllById(List<Long> ids) {
        List<Role> roles = roleRepository.findAllById(ids);
        if(ids.size() != roles.size()) {
            throw new EmptyResultDataAccessException("Not found all roles", ids.size());
        }

        return roles;
    }

    @Override
    public Optional<Role> getByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

}
