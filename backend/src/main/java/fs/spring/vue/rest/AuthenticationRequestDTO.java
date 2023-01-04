package fs.spring.vue.rest;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String username;
    private String password;

}
