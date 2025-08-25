package com.dev001.identify.controller;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.ApiResponse;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .data(userService.getAllUsers())
                .build();
    }
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .data(userService.createUser(request))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .data(userService.getUserDetail(id))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .data(userService.updateUser(id, request))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return ApiResponse.<String>builder()
                .code(1000)
                .message("User deleted successfully")
                .build();
    }
}
