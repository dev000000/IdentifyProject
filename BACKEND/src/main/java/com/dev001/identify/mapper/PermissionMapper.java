package com.dev001.identify.mapper;

import org.mapstruct.Mapper;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.entity.permission.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
