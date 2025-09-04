package com.dev001.identify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev001.identify.entity.invalidatedToken.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
