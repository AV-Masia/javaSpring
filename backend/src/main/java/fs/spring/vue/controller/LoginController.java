package fs.spring.vue.controller;

import fs.spring.vue.model.form.LoginRequest;
import fs.spring.vue.model.form.UserDTO;
import fs.spring.vue.repository.UserRepository;
import fs.spring.vue.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest request){
        try {
            String login = request.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, request.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.createToken(login, userDetails.getAuthorities().stream().findFirst().get().getAuthority());

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userDetails.getUsername());
            userDTO.addRoles(userDetails.getAuthorities().stream().findFirst().get().getAuthority());

            return ResponseEntity.ok(new Object[]{userDTO, token});
        } catch (AuthenticationException e) {
            return  new ResponseEntity<>("Invalid email/password combination: " + request.getUsername(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
