package com.ShopProJect.ShopProJect.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Security;
import java.util.Optional;

public class AuditorAwareImpi implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userId="";
        if (authentication != null){
            userId=authentication.getName();
            //로그인 아이디를 조회해서 사용자의 이름을 등록자와 수정자로 지정
        }
        return Optional.of(userId);
    }
    //1월까지 쇼핑몰 사이트 다 완성시키기
}
