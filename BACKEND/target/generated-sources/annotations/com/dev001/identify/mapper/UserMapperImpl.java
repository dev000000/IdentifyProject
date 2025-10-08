package com.dev001.identify.mapper;

import com.dev001.identify.dto.request.RegisterRequest;
import com.dev001.identify.dto.request.UserUpdateRequest;
import com.dev001.identify.dto.response.RegisterResponse;
import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dob( user.getDob() );
        userResponse.role( user.getRole() );

        return userResponse.build();
    }

    @Override
    public List<UserResponse> toUserResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toUserResponse( user ) );
        }

        return list;
    }

    @Override
    public RegisterResponse toRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterResponse.RegisterResponseBuilder registerResponse = RegisterResponse.builder();

        registerResponse.id( user.getId() );
        registerResponse.username( user.getUsername() );
        registerResponse.password( user.getPassword() );
        registerResponse.firstName( user.getFirstName() );
        registerResponse.lastName( user.getLastName() );
        registerResponse.dob( user.getDob() );
        registerResponse.role( user.getRole() );

        return registerResponse.build();
    }

    @Override
    public User toUser(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        user.dob( request.getDob() );
        user.role( request.getRole() );

        return user.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setDob( request.getDob() );
    }
}
