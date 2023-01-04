package fs.spring.vue.controller;

import fs.spring.vue.model.User;
import fs.spring.vue.model.form.RegistrationForm;
import fs.spring.vue.security.RegistrationValidator;
import fs.spring.vue.service.UserService;
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
    public String getUserByContext(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("registrationForm", RegistrationForm.buildFromUser(user));
        return "user";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam(value = "id", required = true) Long id) {
        if (id != null) {
            userService.deleteUserById(id);
        }
        return "redirect:/logout";
    }

    @RequestMapping(value = "/user.html", method = RequestMethod.PUT)
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
