package com.dev001.identify.service.impl;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.dto.response.RoleResponse;
import com.dev001.identify.entity.permission.Permission;
import com.dev001.identify.entity.role.Role;
import com.dev001.identify.mapper.PermissionMapper;
import com.dev001.identify.mapper.RoleMapper;
import com.dev001.identify.repository.PermissionRepository;
import com.dev001.identify.repository.RoleRepository;
import com.dev001.identify.service.PermissionService;
import com.dev001.identify.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        var permissions = permissionRepository.findAllById(request.getPermissions());
        Role role = roleMapper.toRole(request);
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }
}
