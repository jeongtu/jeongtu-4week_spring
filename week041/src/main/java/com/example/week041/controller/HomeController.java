package com.example.week041.controller;

import com.example.week041.domain.UserRoleEnum;
import com.example.week041.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {             // (05. 로그인,로그아웃 기능 구현 / 회원 로그인,로그아웃 UI처리 / 2. 로그인 성공한 회원의 username 표시 / 3. 로그인한 사용자의 username 적용 구현 / 1. Controll에서 model에 'username' 전달)
        model.addAttribute("username", userDetails.getUsername());

        if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {                                    // (07. 관리자 상품 조회 / 상품 조회(관리자용) 구현 / 1. 관리자 회원에 대한 처리 / a. 관리자인 경우에만 model 값 추가)
            model.addAttribute("admin_role", true);                              // "admin_role": true
        }

        return "index";
    }
}





