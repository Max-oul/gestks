package com.liantsoa.kafesmooth.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.liantsoa.kafesmooth.model.Role;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    
}
