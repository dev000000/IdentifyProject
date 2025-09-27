package com.dev001.identify.service;

import java.util.List;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.UserResponse;

public interface UserService {
//    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserDetail(String id);

    UserResponse updateUser(String id, UserUpdateRequest request);

    void deleteUser(String id);

    UserResponse getMyProfile();
}
