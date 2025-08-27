package com.dev001.identify.repository;

import com.dev001.identify.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserName(String s);
}
