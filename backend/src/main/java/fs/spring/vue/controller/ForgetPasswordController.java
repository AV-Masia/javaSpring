package fs.spring.vue.controller;

import fs.spring.vue.email.EmailService;
import fs.spring.vue.model.User;
import fs.spring.vue.model.form.ForgetPasswordForm;
import fs.spring.vue.security.ForgetPasswordValidator;
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
public class ForgetPasswordController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ForgetPasswordValidator forgetPasswordValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(forgetPasswordValidator);
    }

    @GetMapping("/forgetPassword.html")
    public String getResetForm(Model model) {
        model.addAttribute("forgetPasswordForm", new ForgetPasswordForm());
        model.addAttribute("password", false);
        return "forgetPassword";
    }

    @PostMapping(value ="/forgetPassword.html")
    public String forgetPasswordUser(@Valid ForgetPasswordForm forgetPasswordForm,
                                     BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("forgetPasswordForm", forgetPasswordForm);
            model.addAttribute("password", false);
            return "forgetPassword";
        }
        User user = userService.updateUserPassword(forgetPasswordForm.getEmail());
        emailService.sendPassword(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        model.addAttribute("registrationSuccess", "Registered Successfully. You can login.");
        model.addAttribute("password", true);
        return "forgetPassword";
    }
}