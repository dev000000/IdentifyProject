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
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setData(userService.getAllUsers());
        return apiResponse;
    }
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setData(userService.createUser(request));
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setData(userService.getUserDetail(id));
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setData(userService.updateUser(id, request));
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("User deleted successfully");
        return apiResponse;
    }
}
