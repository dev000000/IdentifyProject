package com.dev001.identify.service;

import java.util.List;

import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    void deleteRole(String name);

    List<RoleResponse> getAllRoles();
}
