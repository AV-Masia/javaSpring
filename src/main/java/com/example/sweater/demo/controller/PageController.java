package com.example.sweater.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping(value = {"/", "/index", "/index.html"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping(value = {"/login.html"})
    public String getLogin(){
        return "login";
    }

    @GetMapping(value = {"/header.html"})
    public String getHeader(){
        return "structure/header";
    }

    @GetMapping(value = {"/footer.html"})
    public String getFooter(){
        return "structure/footer";
    }

    @GetMapping(value = {"/test"})
    public String getTestPage(){
        return "test";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null)
            model.addAttribute("error", "Invalid username and Password");
        if (logout != null){
            model.addAttribute("logout", "You have logged out successfully");
        }
        return "index";
    }
}

