package com.dev001.identify.service.impl;

import com.dev001.identify.dto.response.UserResponse;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.mapper.UserMapper;
import com.dev001.identify.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class UserServiceImplTest {

//  InjectMocks: create an actual instance of the class under test and automatically inject the mock dependencies into it.
    @InjectMocks
    private UserServiceImpl underTest;

//  Mock: create a mock instance of the class under test and inject it into the class under test.
    @Mock private UserRepository userRepository;
    @Mock private UserMapper userMapper;


    private User user;
    private UserResponse userResponse;

//  BeforeEach: run before each test method.
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id("1")
                .userName("test")
                .passWord("")
                .firstName("test")
                .lastName("test")
                .dob(LocalDate.parse("2007-08-28"))
                .roles(new HashSet<>())
                .build();
        userResponse = UserResponse.builder()
                .id("1")
                .userName("test")
                .passWord("")
                .firstName("test")
                .lastName("test")
                .dob(LocalDate.parse("2007-08-28"))
                .roles(new HashSet<>())
                .build();
    }

//  @Test: marks a method as a unit test.
    @Test
//  @Disabled: disable test method, this method will not be executed when you run the test class.
    @Disabled

    void createUser() {
    }

    @Test
//  this is a naming convention for test method name
//  methodName_StateUnderTest_ExpectedBehavior()
    void getAllUsers_shouldReturnMappedList() {
        // given: input, create mock bean behavior (input -> output)
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toUserResponse(List.of(user))).thenReturn(List.of(userResponse));
        // when: method under test
        List<UserResponse> outPut = underTest.getAllUsers();
        // then: assert and verify expected behavior
        assertThat(outPut).hasSize(1);
        verify(userRepository).findAll();
        verify(userMapper).toUserResponse(List.of(user));

    }

    @Test
    @Disabled
    void getUserDetail() {
    }

    @Test
    @Disabled
    void getMyProfile() {
    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    void deleteUser_shouldCallRepository() {
        // given
        // when
        underTest.deleteUser("1");
        // then
        verify(userRepository).deleteById("1");

    }
}