package com.dev001.identify.service;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    void deletePermission(String name);
    List<PermissionResponse> getAllPermissions();
}
