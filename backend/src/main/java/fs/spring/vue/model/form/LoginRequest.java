package fs.spring.vue.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginRequest {

    @NotBlank(message = "Username can not be empty")
    private String login;
    @NotBlank(message = "Password can not be empty")
    private String password;
}
