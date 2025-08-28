package com.dev001.identify.controller;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.request.RoleRequest;
import com.dev001.identify.dto.response.ApiResponse;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.dto.response.RoleResponse;
import com.dev001.identify.repository.RoleRepository;
import com.dev001.identify.service.PermissionService;
import com.dev001.identify.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles(){
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .data(roleService.getAllRoles())
                .build();
    }
    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .data(roleService.createRole(request))
                .build();
    }
    @DeleteMapping("/{role}")
    public ApiResponse<Void> deleteRole(@PathVariable String role){
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }
}
