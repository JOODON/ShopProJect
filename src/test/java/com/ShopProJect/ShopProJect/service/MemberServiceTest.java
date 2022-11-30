package com.ShopProJect.ShopProJect.service;

import com.ShopProJect.ShopProJect.Service.MemberService;
import com.ShopProJect.ShopProJect.dto.MemberFromDto;
import com.ShopProJect.ShopProJect.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
//어노테이선 생성
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {
    //UserDetailsService를 구현합니다.
    @Autowired
    MemberService memberService;

    //실제 객체를 이용하면 웹 브라우저에서 쓰는거 처럼 테스트 가능
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFromDto memberFromDto=new MemberFromDto();
        memberFromDto.setEmail("test@email.com");
        memberFromDto.setName("주동호");
        memberFromDto.setAddress("인천시 주안동");
        memberFromDto.setPassword("1234");
        return Member.createMember(memberFromDto,passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member= createMember();
        Member saveMember=memberService.saveMember(member);

        assertEquals(member.getEmail(),saveMember.getEmail());
        assertEquals(member.getName(),saveMember.getName());
        assertEquals(member.getAddress(),saveMember.getAddress());
        assertEquals(member.getPassword(),saveMember.getPassword());
        assertEquals(member.getRole(),saveMember.getRole());
    }
    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateMemberTest(){
        Member member1=createMember();
        Member member2=createMember();
        memberService.saveMember(member1);

        Throwable e=assertThrows(IllegalStateException.class,()->{
            memberService.saveMember(member2);
        });
        assertEquals("이미 가입된 회원입니다.",e.getMessage());
    }

}
