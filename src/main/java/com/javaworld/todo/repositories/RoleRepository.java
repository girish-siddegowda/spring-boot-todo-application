package com.javaworld.todo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaworld.todo.model.AppRole;
import com.javaworld.todo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}
