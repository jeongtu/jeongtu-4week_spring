package com.example.week041.security;

import com.example.week041.domain.User;
import com.example.week041.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {                                      // (05. 로그인,로그아웃 기능 구현 / 로그인,로그아웃 구현 / 2. DB의 회원 정보 조회 -> 스프링 시큐리티의 인증 관리자에게 전달 / a. UserDetailsService 구현 / i. UserDetailsService 인터페이스 -> UserDetailsServicelmpl 클래스)
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)                                             // (05. 로그인,로그아웃 기능 구현 / 로그인 처리 과정 / 2. 인증관리자 / a. UserDeailsService에게 username을 전달하고 회원상세 정보를 요청)
                                                                                                        // (05. 로그인,로그아웃 기능 구현 / 로그인 처리 과정 / 3. UserDetailsService / a. 회원 DB에서 회원 조회 - 존재하지 않을시 Error 발생)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(user);                                                               // (05. 로그인,로그아웃 기능 구현 / 로그인 처리 과정 / 3. UserDetailsService / b. 조회된 회원 정보(user)를 UserDetails로 변환)
                                                                                                        // (05. 로그인,로그아웃 기능 구현 / 로그인 처리 과정 / 3. UserDetailsService / c. UserDetails를 인증관리자에게 전달)
    }
}