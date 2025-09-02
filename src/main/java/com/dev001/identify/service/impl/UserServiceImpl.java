package com.dev001.identify.service.impl;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.enums.Role;
import com.dev001.identify.exception.AppException;
import com.dev001.identify.mapper.UserMapper;
import com.dev001.identify.repository.RoleRepository;
import com.dev001.identify.repository.UserRepository;
import com.dev001.identify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.dev001.identify.exception.ErrorCode.USER_EXISTED;
import static com.dev001.identify.exception.ErrorCode.USER_NOT_FOUND;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder BcryptPasswordEncoder;

    @Override
    public UserResponse createUser(UserCreationRequest request) {

        if(userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String hashedPassword = passwordEncoder.encode(request.getPassWord());
        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setPassWord(hashedPassword);
//        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }


    @Override
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<UserResponse> getAllUsers() {
        return userMapper.toUserResponse(userRepository.findAll());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserDetail(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
    @Override
//    @PostAuthorize("returnObject.userName == authentication.name")
    public UserResponse getMyProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUserName(name)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    @Override

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        var roles = roleRepository.findAllById(request.getRoles());
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(USER_NOT_FOUND));
        user.setRoles(new HashSet<>(roles));
        userMapper.updateUser(user, request);
        user.setPassWord(BcryptPasswordEncoder.encode(request.getPassWord()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
