package com.example.week041.repository;

import com.example.week041.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {                                         // (03. 회원가입 가입 기능 구현 / 회원가입 API 구현 / 4. 회원가입 API Repository 구현)
    Optional<User> findByUsername(String username);
}