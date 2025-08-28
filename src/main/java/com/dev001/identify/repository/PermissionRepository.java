package com.dev001.identify.repository;

import com.dev001.identify.entity.permission.Permission;
import com.dev001.identify.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String> {

}
