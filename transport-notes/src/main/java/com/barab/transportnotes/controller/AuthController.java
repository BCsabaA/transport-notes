package com.barab.transportnotes.controller;

import com.barab.transportnotes.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    //handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return"index";
    }

    //handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        //create a model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
        //TODO: register.html folytatása navbar hozzádásával (259-es lecke)
    }

}
