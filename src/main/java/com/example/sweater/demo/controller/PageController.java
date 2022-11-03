package com.example.sweater.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping(value = {"/", "/index"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String getLogin(){
        return "login";
    }

    @GetMapping(value = {"/reset"})
    public String getReset(){
        return "reset";
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

