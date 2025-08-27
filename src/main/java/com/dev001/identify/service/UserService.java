package com.dev001.identify.service;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserResponse createUser(UserCreationRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserDetail(String id);
    UserResponse updateUser(String id, UserUpdateRequest request);
    void deleteUser(String id);
    UserResponse getMyProfile();



}
