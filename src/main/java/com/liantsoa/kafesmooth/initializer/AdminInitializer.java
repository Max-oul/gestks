package com.liantsoa.kafesmooth.initializer;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.liantsoa.kafesmooth.model.Role;
import com.liantsoa.kafesmooth.model.User;
import com.liantsoa.kafesmooth.repository.RoleRepository;
import com.liantsoa.kafesmooth.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

    private static final String ADMIN_USERNAME = "adminKs";
    private static final String ADMIN_PASSWORD = "ksManager#123";
    private static final String ADMIN_ROLE = "ROLE_MANAGER";

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing admin user...");
        Optional<Role> roleManagerOptional = roleRepository.findByName(ADMIN_ROLE);
        if (roleManagerOptional.isEmpty()) {
            logger.error("Role {} not found", ADMIN_ROLE);
            return;
        }
        Role roleManager = roleManagerOptional.get();
        Optional<User> adminUserOptional = userRepository.findByUsername(ADMIN_USERNAME);
        if (adminUserOptional.isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername(ADMIN_USERNAME);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            adminUser.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            adminUser.setRoles(Set.of(roleManager));
            userRepository.save(adminUser);
            logger.info("Admin user created with username: {}", ADMIN_USERNAME);
        } else {
            logger.info("Admin user already exists with username: {}", ADMIN_USERNAME);
        }
        logger.info("Admin user initialization complete.");
    }
}