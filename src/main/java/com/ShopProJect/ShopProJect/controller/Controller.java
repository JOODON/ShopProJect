package com.ShopProJect.ShopProJect.controller;

import com.ShopProJect.ShopProJect.dto.ItemDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/thymeleaf")
public class Controller {
    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model){
        model.addAttribute("data","타임리프 예제입니다");
        return "thymeleafEx/thymeleafEx01";
    }
    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ItemDto itemDto=new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemNm("테스트 상품1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto",itemDto);
        return "thymeleafEx/thymeleafEx02";
    }
}
