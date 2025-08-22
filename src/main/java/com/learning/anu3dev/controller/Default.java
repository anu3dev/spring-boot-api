package com.learning.anu3dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Default {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World!";
    }
}
