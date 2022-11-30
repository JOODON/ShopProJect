package com.ShopProJect.ShopProJect.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class itemController {
    @GetMapping(value = "/admin/item/new")
    public String itemForm(){
        return "/item/itemFrom";
    }
}
