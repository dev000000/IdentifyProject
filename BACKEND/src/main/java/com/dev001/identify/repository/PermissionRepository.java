package com.dev001.identify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev001.identify.entity.permission.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {}
