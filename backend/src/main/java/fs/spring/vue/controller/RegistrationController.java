package fs.spring.vue.controller;

import fs.spring.vue.model.form.RegistrationForm;
import fs.spring.vue.security.RegistrationValidator;
import fs.spring.vue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }

    @GetMapping("/registration.html")
    public String getRegistrationForm(Model model) {
        RegistrationForm registrationForm = new RegistrationForm();
        model.addAttribute("registrationForm", registrationForm);
        return "registration";
    }

    @PostMapping(value ="/registration.html")
    public String registerUser(@Valid RegistrationForm registrationForm,
                                   BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("registrationForm", registrationForm);
            return "registration";
        }
        userService.createUserFromRegistrationForm(registrationForm);
        model.addAttribute("registrationSuccess", "Registered Successfully. You can login.");
        return "login";
    }
}