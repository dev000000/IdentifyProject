package com.dev001.identify.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.response.RoleResponse;
import com.dev001.identify.entity.role.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
