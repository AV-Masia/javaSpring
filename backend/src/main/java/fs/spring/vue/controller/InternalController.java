package fs.spring.vue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class InternalController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/internal")
    public ResponseEntity<String> print() {
        return new ResponseEntity<>("I AM PROJECTED BY THE TOKEN hasRole('ADMIN')", HttpStatus.OK);
    }
}
