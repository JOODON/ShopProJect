package com.ShopProJect.ShopProJect.controller;

import com.ShopProJect.ShopProJect.Service.MemberService;
import com.ShopProJect.ShopProJect.dto.MemberFromDto;
import com.ShopProJect.ShopProJect.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFromDto",new MemberFromDto());
        return "member/memberFrom";
    }
    @PostMapping(value = "/new")
    public String memberFrom(MemberFromDto memberFromDto){
        Member member=Member.createMember(memberFromDto,passwordEncoder);
        memberService.saveMember(member);

        return "redirect:/";
    }
}
