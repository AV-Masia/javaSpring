package com.example.sweater.demo.controller;

import com.example.sweater.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

}
