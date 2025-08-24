package com.dev001.identify.repository;

import com.dev001.identify.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserName(String s);
    Optional<User> findByUserName(String userName);
}
