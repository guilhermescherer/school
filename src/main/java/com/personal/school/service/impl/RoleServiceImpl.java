package com.personal.school.service.impl;

import com.personal.school.model.Role;
import com.personal.school.model.Subject;
import com.personal.school.repository.RoleRepository;
import com.personal.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllByIdThrow(List<Long> ids) {

        if(isNull(ids)) return EMPTY_LIST;

        List<Role> roles = roleRepository.findAllById(ids);

        if(ids.size() != roles.size()) {
            throw new EmptyResultDataAccessException("Not found all roles", ids.size());
        }

        return roles;
    }

}
