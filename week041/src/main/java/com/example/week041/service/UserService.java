package com.example.week041.service;

import com.example.week041.domain.User;
import com.example.week041.domain.UserRoleEnum;
import com.example.week041.repository.UserRepository;
import com.example.week041.dto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {                                                                      // (03. 회원가입 가입 기능 구현 / 회원가입 API 구현 / 3. 회원가입 API Service 구현)
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;                                                // DB에 접근할 수 있는 Repository라는 역할을 사용할 수 있도록 정의
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";             // ADMIN_TOKEN은 상수 형태로 선언이 되어서 변경되지 않도록 처리가 됨

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void registerUser(SignupRequestDto requestDto) {                                     // registerUser 새로운 유저를 생성하는 함수에서 DTO로 받은 데이터로 애네들에게 GET 함수를 적용한다음에
                                                                                                // 각각의 변수에 할당을 해주고 이 할당된 값을 다시 Repository에 전달해주는 과정
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());                     // (04. 패스워드 암호화 구현 / 패스워드 암호화 적용 / 2. 회원 가입 시 패스워드 암호화 구현)
        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }
}
