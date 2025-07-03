package com.example.login_logout1.repository;

import com.example.login_logout1.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    boolean existsById(Long id);
    Role findById(long id);
}
