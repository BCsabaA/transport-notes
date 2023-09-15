package com.barab.transportnotes.controller;

import com.barab.transportnotes.dto.UserDto;
import com.barab.transportnotes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

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
    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }


}
