package com.example.week041.domain;

public enum UserRoleEnum {                                                      // 고정된 카테고리 값들(역할)을 정하는 것
                                                                                // (03. 회원가입 가입 기능 구현 / 회원 DB에 매핑되는 @Entity 클래스 구현)
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {                                           // 시큐리티를 이용한 API별 권한 제어
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}