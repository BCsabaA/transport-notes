package com.barab.transportnotes.controller;

import com.barab.transportnotes.dto.UserDto;
import com.barab.transportnotes.entity.User;
import com.barab.transportnotes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    //handler method to handel login request
    @GetMapping("/login")
    public String login() {
        return "login";
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
    public String registration(@Valid @ModelAttribute("user") UserDto userDto , BindingResult bindingResult, Model model) {
        User existing = userService.findUserByEmail(userDto.getEmail());

        if (existing != null && existing.getEmail() != null && !existing.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", null, "There is already an account existing with the same email!");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }


        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    //handler method to handle list of users
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    //TODO: role http beállításai
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        //ha nem admin akkor indexre küldeni
        return "users";
    }

    @GetMapping("/notes")
    public String test() {
        return "notes";
    }

    @ModelAttribute("loggedInUser")
    public org.springframework.security.core.userdetails.User globalUserObject(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("loggedInUser", authentication.getName());
        model.addAttribute("isAdmin",isAdmin(authentication.getAuthorities()));
        model.addAttribute("roles",authentication.getAuthorities());
        return new org.springframework.security.core.userdetails.User(
                authentication.getName(),
                "",
                authentication.getAuthorities());
    }

    private boolean isAdmin(Collection<? extends GrantedAuthority> authorities) {
        boolean isAdmin = false;
        for (GrantedAuthority authority : authorities
        ){
            if (Objects.equals(authority.getAuthority(), "ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        return isAdmin;
    }


}
