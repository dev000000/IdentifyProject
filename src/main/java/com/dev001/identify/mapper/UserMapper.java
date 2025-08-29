package com.dev001.identify.mapper;

import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponse(List<User> users);
    @Mapping(target = "roles" , ignore = true )
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
