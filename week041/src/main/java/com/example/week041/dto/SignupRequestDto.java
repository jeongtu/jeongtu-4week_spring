package com.example.week041.dto;

import lombok.Getter;
import lombok.Setter;
                                                                                    // DTO : 데이터를 나르는 심부름꾼
@Setter
@Getter
public class SignupRequestDto {                                                     // 기본값이 들어가있는 컬럼이 있다
                                                                                    // (03. 회원가입 가입 기능 구현 / 회원가입 API 구현 / 1. 회원가입 요청 DTO 구현)
    private String username;
    private String password;
    private String email;
    private boolean admin = false;                                                  // admin = false = 일반유저
    private String adminToken = "";
}