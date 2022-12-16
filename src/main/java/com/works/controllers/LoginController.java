package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String adminLogin(@RequestParam String email, @RequestParam String password, Model model) {
        System.out.println(email + " " + password);
        model.addAttribute("email", email);
        return "login";
    }

}
