package fs.spring.vue.controller;

import fs.spring.vue.model.form.RegistrationForm;
import fs.spring.vue.security.RegistrationValidator;
import fs.spring.vue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
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

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        RegistrationForm registrationForm = new RegistrationForm();
        model.addAttribute("registrationForm", registrationForm);
        return "registration";
    }

    @PostMapping(value ="/registration" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationForm registrationForm,
                                          BindingResult result) {
        Map<Object, Object> response = new HashMap<>();
        response.put("registrationForm", registrationForm);
        if (result.hasErrors()){
            response.put("status", "Registered failed");
            response.put("result", result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        userService.createUserFromRegistrationForm(registrationForm);
        response.put("status", "Registered Successfully. You can login.");
       return ResponseEntity.ok(response);
    }
}