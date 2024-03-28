package com.example.chapter10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/main")
    public String main() {
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
    @CrossOrigin("http://localhost:8080") //localhost 출처에 대한 교차 출처 요청을 허용
    public String test() {
        logger.info("Test method called");
        return "HELLO!";
    }
}
