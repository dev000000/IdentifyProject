package com.dev001.identify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.entity.permission.Permission;
import com.dev001.identify.mapper.PermissionMapper;
import com.dev001.identify.repository.PermissionRepository;
import com.dev001.identify.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public void deletePermission(String name) {
        permissionRepository.deleteById(name);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permission -> permissionMapper.toPermissionResponse(permission))
                .toList();
    }
}
