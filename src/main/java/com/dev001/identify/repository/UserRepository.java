package com.dev001.identify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev001.identify.entity.user.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserName(String s);

    Optional<User> findByUserName(String userName);
}
