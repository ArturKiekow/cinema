package com.arturFerreira.cinema.repository;

import com.arturFerreira.cinema.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

}
