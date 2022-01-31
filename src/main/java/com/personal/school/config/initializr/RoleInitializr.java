package com.personal.school.config.initializr;

import com.personal.school.model.Role;
import com.personal.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Optional;

import static com.personal.school.utils.ConstUtils.ROLE_ADMIN;
import static com.personal.school.utils.ConstUtils.ROLE_USER;

public class RoleInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createRoleIfNecessary(ROLE_USER);
        createRoleIfNecessary(ROLE_ADMIN);
    }

    private void createRoleIfNecessary(String name) {
        Optional<Role> optionalRole = roleService.getByName(name);
        if(!optionalRole.isPresent()) {
            roleService.save(new Role(name));
        }
    }

}
