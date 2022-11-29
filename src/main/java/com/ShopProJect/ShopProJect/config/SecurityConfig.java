package com.ShopProJect.ShopProJect.config;

import com.ShopProJect.ShopProJect.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//WebSecurityConfigurerAdapter 를 상속받는 클래스를 포함하면 자동적으로 시큐리티 체인이 포함됨 오버라이딩을 통한 보안설정 가능!
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http 요청에 대한 보안 설정
        http.formLogin().loginPage("/members/login")//로그인 페이지 URL을 설정합니다.
                .defaultSuccessUrl("/")//로그인 성공 시 이동할 URL을 설정
                .usernameParameter("email")//로그인 시 사용할 파라미터 이름으로 email 을 지정합니다.
                .failureUrl("/member/login/error")//로그인 실패시 이동할 URL을 설정
                .and().logout().
                logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))//로그아웃 URL 을 설정
                .logoutSuccessUrl("/");//로그아웃 성공시 이동할 URL 을 설정

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        //비밀번호를 암호화 시킴
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //AuthenticationManager를 통해서 인증을 이루어짐 그걸 생성 userDetailService를 구현하고있는 객체로
        //MemberService를 지정해줌 암호화도 시켜주기!
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}
