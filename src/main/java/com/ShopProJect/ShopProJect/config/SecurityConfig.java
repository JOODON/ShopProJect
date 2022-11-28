package com.ShopProJect.ShopProJect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//WebSecurityConfigurerAdapter 를 상속받는 클래스를 포함하면 자동적으로 시큐리티 체인이 포함됨 오버라이딩을 통한 보안설정 가능!
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http 요청에 대한 보안 설정
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        //비밀번호를 암호화 시킴
        return new BCryptPasswordEncoder();
    }
}
