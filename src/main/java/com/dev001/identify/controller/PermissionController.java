package com.dev001.identify.controller;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.response.ApiResponse;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermissions(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .data(permissionService.getAllPermissions())
                .build();
    }
    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .data(permissionService.createPermission(request))
                .build();
    }
    @DeleteMapping("/{pemission}")
    public ApiResponse<Void> deletePermission(@PathVariable String pemission){
        permissionService.deletePermission(pemission);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }
}
