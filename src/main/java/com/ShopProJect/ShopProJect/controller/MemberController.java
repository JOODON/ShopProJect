package com.ShopProJect.ShopProJect.controller;

import com.ShopProJect.ShopProJect.Service.MemberService;
import com.ShopProJect.ShopProJect.dto.MemberFromDto;
import com.ShopProJect.ShopProJect.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){

        model.addAttribute("memberFromDto",new MemberFromDto());

        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberFrom(@Valid MemberFromDto memberFromDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
            //에러가 있을시 회원가입 페이지로 이동시켜주는 부분!
        }
        try {
            Member member=Member.createMember(memberFromDto,passwordEncoder);
            //비밀번호를 암호화 시켜주고 아이디값을 검증해서 넘겨줌
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            //중복 가입 예외가 발생할시에 메시지를 띄워줌!
            return "member/memberForm";
        }
        return "redirect:/";
    }
}
