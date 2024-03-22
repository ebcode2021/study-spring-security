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

    @PostMapping("/hello") // CSRF 보호 적용할 경로
    public String postHello() {
        return "This is Post Hello~";
    }

    @PostMapping("/ciao") // CSRF 보호 적용X 경로
    public String postCiao() {
        return "This is Post Ciao~";
    }
}
