package fs.spring.vue.controller;

import fs.spring.vue.model.Genre;
import fs.spring.vue.model.User;
import fs.spring.vue.model.form.RegistrationForm;
import fs.spring.vue.security.RegistrationValidator;
import fs.spring.vue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }


    @GetMapping(value = "/user")
    public ResponseEntity<User> getUser(@RequestParam(value = "userName", required = true)String userName) {
        User user = userService.getUserByEmail(userName);
        return user != null
                ? ResponseEntity.ok().body(user)
                : ResponseEntity.internalServerError().build();
    }



    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam(value = "id", required = true) Long id) {
        return userService.deleteUserById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value ="/user" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody @Valid RegistrationForm registrationForm,
                                          BindingResult result) {
        Map<Object, Object> response = new HashMap<>();
        response.put("registrationForm", registrationForm);
        if (result.hasErrors()){
            response.put("status", "Registered failed");
            response.put("result", result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        userService.updateUser(registrationForm);
        response.put("status", "Updated Successfully.");
        return ResponseEntity.ok(response);
    }
}
