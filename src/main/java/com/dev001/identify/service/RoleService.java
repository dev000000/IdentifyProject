package com.dev001.identify.service;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    void deleteRole(String name);
    List<RoleResponse> getAllRoles();
}
