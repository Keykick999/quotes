package com.example.Quotist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/quotes")
    public String showQuotes() {
        return "quotes";
    }
}
