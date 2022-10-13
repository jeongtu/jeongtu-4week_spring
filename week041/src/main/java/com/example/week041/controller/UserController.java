package com.example.week041.controller;

import com.example.week041.dto.SignupRequestDto;
import com.example.week041.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller                                                     // Model 객체를 만들어 데이터를 담고 View를 반환
//@RestController                                               // 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아 전송
                                                                // RestController = Controller + ResponseBody

public class UserController {                                   // 로그인 페이지 및 singup페이지들을 브라우저에서 요청했을떄 특정페이지로 보는 관문 역할
                                                                // (02. 회원가입 UI 반영 / 파일 생성)

    private final UserService userService;

    @Autowired                                                  // 스프링에서 bean 인스턴스가 생성된 이후 autowired를 설정한 메서드가 자동으로 호출되고, 인스턴스가 자동으로 주입
                                                                // 해당 변수 및 메서드에 스프링이 관리하는 bean을 자동으로 매핑해주는 개념
                                                                // 변수,setter메서드,생성자,일반메서드에 적용이 가능
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")                                  // 유저 로그인으로 들어온 사용자의 GET요청을 login.html로 우리가 redirect한다
    public String login() {
        return "login";
    }                     // return : 파일 위치를 찾아감

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")                                // POST : 데이터를 짊어지고 날라서 새로운 데이터를 생성하는 역할로서, 데이털르 Service에게 던져준다
                                                                  // (03. 회원가입 가입 기능 구현 / 회원가입 API 구현 / 2. 회원관리 Controller 구현)
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";                              // redirect : 해당 url을 찾아감
    }
}