package com.dev001.identify.mapper;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.dto.response.RoleResponse;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.permission.Permission;
import com.dev001.identify.entity.role.Role;
import com.dev001.identify.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
