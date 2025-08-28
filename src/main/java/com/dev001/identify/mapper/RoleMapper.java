package com.dev001.identify.mapper;

import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.RoleResponse;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.role.Role;
import com.dev001.identify.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
