package com.ShopProJect.ShopProJect.Service;

import com.ShopProJect.ShopProJect.entity.Member;
import com.ShopProJect.ShopProJect.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional//로직을 처리하다가 오류가 날시 그 전으로 롤백시켜줌
@RequiredArgsConstructor //Autowired대신 이걸로 빈 주입을 해줌
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    //중복 회원가입 방지를 위한 예외처리!
    private void validateDuplicateMember(Member member){
        Member findMember=memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member=memberRepository.findByEmail(email);
        if(member==null){
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                //User 객체 를 반환해주고 생성자의 회원의 이메일 비밀번호 role을 넘겨줍니다.
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
