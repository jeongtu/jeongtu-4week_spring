package com.example.week041.config;

import com.example.week041.jwt.JwtAuthenticationFilter;
import com.example.week041.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity                                                                      // 스프링 Security 지원을 가능하게 함(01. 스프링 시큐리티 활성화)
@EnableGlobalMethodSecurity(securedEnabled = true)                                      // @Secured 어노테이션 활성화(접근 불가 페이지 설정)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {                   // 로그인 페이지 설정(02. 회원가입 UI 반영 / 로그인 페이지 설정)


    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers( "/login/google").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
}