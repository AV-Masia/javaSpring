package fs.spring.vue.model.form;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class ForgetPasswordForm {

    private String email;
}