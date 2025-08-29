package com.dev001.identify.repository;

import com.dev001.identify.entity.invalidatedToken.InvalidatedToken;
import com.dev001.identify.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {

}
