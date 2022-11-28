package com.ShopProJect.ShopProJect.entity;

import com.ShopProJect.ShopProJect.constant.Role;
import com.ShopProJect.ShopProJect.dto.MemberFromDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    //중복값 제거
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    //enum 타입을 String 으로 저장!
    private Role role;

    public static Member createMember(MemberFromDto memberFromDto, PasswordEncoder passwordEncoder){
        Member member=new Member();
        member.setName(memberFromDto.getName());
        member.setEmail(memberFromDto.getEmail());
        member.setAddress(memberFromDto.getAddress());
        String password=passwordEncoder.encode(memberFromDto.getPassword());
        //이쪽으로 넘겨서 비밀번호를 암호화 시켜주는 부분!
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}
