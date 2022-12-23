package com.ShopProJect.ShopProJect.controller;

import com.ShopProJect.ShopProJect.dto.ItemFromDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class itemController {
    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFromDto", new ItemFromDto());
        return "/item/itemForm";
    }
}
