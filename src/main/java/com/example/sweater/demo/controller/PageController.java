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

    @GetMapping(value = {"/header.html"})
    public String getHeader(){
        return "structure/header";
    }

    @GetMapping(value = {"/footer.html"})
    public String getFooter(){
        return "structure/footer";
    }

    @GetMapping(value = {"/reset.html"})
    public String getReset(){
        return "reset";
    }

    @RequestMapping("/login.html")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null)
            model.addAttribute("error1", true);
        return "login";
    }
}

