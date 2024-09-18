package com.vnua.blog.controller;

import com.vnua.blog.dto.request.AuthenticationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeController {
    @GetMapping
    public String home() {
        return "Home";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }
    @GetMapping("/userPage")
    public String userPage() {
        return "UserPage";
    }

}