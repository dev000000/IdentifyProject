package com.dev001.identify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev001.identify.entity.role.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
