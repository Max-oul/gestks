package com.liantsoa.kafesmooth.initializer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.liantsoa.kafesmooth.repository.RoleRepository;
import com.liantsoa.kafesmooth.model.Role;

@Component
public class RoleInitializer implements CommandLineRunner{
    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(RoleInitializer.class);
    //List of default roles
    private static final List<String> DEFAULT_ROLES = Arrays.asList("ROLE_MANAGER", "ROLE_EMPLOYEE");

    @Override
    public void run(String... args) throws Exception {
       logger.info("Initializing Roles");
       for(String roleName : DEFAULT_ROLES){
            Optional<Role> existingRole = roleRepository.findByName(roleName);
            if(existingRole.isEmpty()){
                roleRepository.save(new Role(null, roleName));
                logger.info("Created role: {}", roleName);
            } else {
                logger.info("Role alread exists: {}", roleName);
            }
       }
       logger.info("Roles initialization complete"); 

    }
}
