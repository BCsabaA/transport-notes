package com.barab.transportnotes.controller;

import com.barab.transportnotes.dto.*;
import com.barab.transportnotes.entity.User;
import com.barab.transportnotes.service.AddressService;
import com.barab.transportnotes.service.ItemService;
import com.barab.transportnotes.service.StatusService;
import com.barab.transportnotes.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private ItemService itemService;
    private AddressService addressService;
    private StatusService statusService;

//    public AuthController(UserService userService, ItemService itemService) {
//        this.userService = userService;
//        this.itemService = itemService;
//    }

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

    @GetMapping("/new-note")
    public String showNewNoteForm(Model model) {
        //create a model object to store form data
        NoteDto note = new NoteDto();
        model.addAttribute("note", note);

        //get the choice list for statuses select
        List<AddressDto> addresses = addressService.findAllItems().
                stream().
                sorted((addressDto1, addressDto2) ->  addressDto1.getName().compareTo(addressDto2.getName()) ).
                collect(Collectors.toList());
        model.addAttribute("addresses", addresses);

        //TODO: címek keresése a felületen -> kulcsszó kereséses legördülő mező
        //TODO: FORM kitöltési ellenőrzés



        //get the choice list for statuses select
        List<String> statuses = statusService.findAllNames();
        model.addAttribute("statuses", statuses);

        model.addAttribute("testNumber",5);

        return "new-note";
    }

    @GetMapping("/new-address")
    public String showNewAddressForm(Model model) {
        AddressDto newAddress = new AddressDto();
        model.addAttribute("newAddress",newAddress);
        return "/new-address";
    }

    @PostMapping("/new-address/save")
    public String saveNewAddress(@ModelAttribute("newAddress") AddressDto addressDto, BindingResult bindingResult, Model model) {
        System.out.println(addressDto.toString());
        addressService.saveAddress(addressDto);
        return "/new-address";
    }

    @PostMapping("/new-note/save")
    public String saveNewNote(@ModelAttribute("note") NoteDto noteDto, BindingResult bindingResult, Model model) {
        System.out.println(noteDto.toString());



        return "/notes";
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
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        //ha nem admin akkor indexre küldeni
        return "users";
    }

    @GetMapping("/notes")
    public String showNotes() {
        return "notes";
    }

    @GetMapping("/items")
    public String items(Model model) {
        List<ItemDto> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/addresses")
    public String addresses(Model model) {
        List<AddressDto> addresses = addressService.findAllItems();
        model.addAttribute("addresses", addresses);
        return "addresses";
    }

    @GetMapping("/statuses")
    public String statuses(Model model) {
        List<StatusDto> statuses = statusService.findAllItems();
        model.addAttribute("statuses", statuses);
        return "statuses";
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
