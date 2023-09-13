package com.barab.transportnotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    //handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return"index";
    }

}
