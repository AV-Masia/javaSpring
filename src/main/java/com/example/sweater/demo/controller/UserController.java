package com.example.sweater.demo.controller;

import com.example.sweater.demo.model.User;
import com.example.sweater.demo.model.form.RegistrationForm;
import com.example.sweater.demo.security.RegistrationValidator;
import com.example.sweater.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.sweater.demo.model.form.RegistrationForm.buildFromUser;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }


    @GetMapping(value = {"/user.html"})
    public String getUserById(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("registrationForm", buildFromUser(user));
        return "user";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam(value = "id", required = true) Long id) {
        if (id != null) {
            userService.deleteUserById(id);
        }
        return "redirect:/logout";
    }

    @PostMapping(value = "/user.html")
    public String updateUser(@Valid RegistrationForm registrationForm,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("registrationForm", registrationForm);
            return "user";
        }
        userService.updateUser(registrationForm);
        model.addAttribute("updated", true);
        return "user";
    }
}
