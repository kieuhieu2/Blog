package com.vnua.blog.controller;

import com.vnua.blog.dto.response.BlogResponse;
import com.vnua.blog.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final BlogService blogService;

    @GetMapping
    public String home(Model model, @RequestParam(value = "token", required = false) String token) {
        List<BlogResponse> blogs = blogService.getBlog();
        model.addAttribute("content", "home"); // Renders home.html
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("content", "home"); // Renders home.html
        return "main";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("content", "login");
        return "main";
    }

    @GetMapping("/search")
    public String showSearch(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "search");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/blogDetails/{title}")
    public String showDetailsBlog(@PathVariable String title, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("content", "blogDetails");
        return "main";
    }

    @GetMapping("/createBlog")
    public String showCreateBlog(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "createBlog");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/myBlog")
    public String showMyBlog(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "myBlog");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/myInfo")
    public String showMyInfo(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "myInfo");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/user")
    public String showUser(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "user");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/userDetails/{userId}")
    public String showUserDetails(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "userDetails");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/createUser")
    public String showCreateUser(Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("content", "createUser.html");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }

    @GetMapping("/myBlog/updateBlog/{title}")
    public String showUpdateBlog(@PathVariable String title, Model model, @RequestParam(value = "token", required = false) String token) {
        model.addAttribute("title", title);
        model.addAttribute("content", "updateBlog");
        if(token != null) {
            model.addAttribute("token", token);
        }
        return "main";
    }
}