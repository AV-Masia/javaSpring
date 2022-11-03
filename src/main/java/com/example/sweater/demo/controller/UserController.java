package com.example.sweater.demo.controller;

import com.example.sweater.demo.model.dto.UserDTO;
import com.example.sweater.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String  regUserForm(@ModelAttribute(value = "UserDto") UserDTO userDto) {
//        log.info("UserDto send to createUserFromUser, userDTO= " + userDto);
        userService.createUserFromUserDTO(userDto);
        return "redirect:/index";
    }

}
