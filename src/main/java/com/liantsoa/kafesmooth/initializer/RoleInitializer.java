package com.liantsoa.kafesmooth.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.liantsoa.kafesmooth.repository.RoleRepository;
import com.liantsoa.kafesmooth.model.Role;

@Component
public class RoleInitializer implements CommandLineRunner{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName("ROLE_MANAGER").isEmpty()){
            roleRepository.save(new Role(null, "ROLE_MANAGER"));
        }
        if(roleRepository.findByName("ROLE_EMPLOYEE").isEmpty()){
            roleRepository.save(new Role(null, "ROLE_EMPLOYEE"));
        }
    }
}
