package com.dev001.identify.service.impl;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.exception.AppException;
import com.dev001.identify.repository.UserRepository;
import com.dev001.identify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dev001.identify.exception.ErrorCode.USER_EXISTED;
import static com.dev001.identify.exception.ErrorCode.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserCreationRequest request) {
        User user = new User();
        if(userRepository.existsByUserName(request.getUsername())) {
            throw new AppException(USER_EXISTED);
        }

        user.setUserName(request.getUsername());
        user.setPassWord(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserDetail(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));
    }

    @Override
    public User updateUser(String id, UserUpdateRequest request) {
        User user = getUserDetail(id);
        user.setPassWord(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
