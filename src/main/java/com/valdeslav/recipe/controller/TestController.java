package com.valdeslav.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class TestController {
    @GetMapping("/access")
    public String testAccess() {
        return "You have access!";
    }
}
