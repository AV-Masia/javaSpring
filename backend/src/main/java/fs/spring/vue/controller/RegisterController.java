package fs.spring.vue.controller;

import fs.spring.vue.model.form.RegisterRequest;
import fs.spring.vue.model.form.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@RequestBody @Valid RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setRegistered(true);
        registerResponse.setMessage("User was saved");
        return ResponseEntity.ok(registerResponse);
    }
}
