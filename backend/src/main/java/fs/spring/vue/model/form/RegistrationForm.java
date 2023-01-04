package fs.spring.vue.model.form;

import fs.spring.vue.model.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = "email")
@ToString(exclude = {"confirmPassword"})
public class RegistrationForm {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public static RegistrationForm buildFromUser(User user){
        return RegistrationForm.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}