package com.example.chapter10.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello~!";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "This is Post Hello~";
    }
}
