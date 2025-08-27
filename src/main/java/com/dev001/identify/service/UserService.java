package com.dev001.identify.service;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User createUser(UserCreationRequest request);
    List<User> getAllUsers();
    User getUserDetail(String id);
    User updateUser(String id, UserUpdateRequest request);
    void deleteUser(String id);



}
