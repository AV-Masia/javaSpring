package fs.spring.vue.security;

import fs.spring.vue.model.form.ForgetPasswordForm;
import fs.spring.vue.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@ConfigurationProperties(prefix = "validation")
public class ForgetPasswordValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ForgetPasswordForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ForgetPasswordForm forgetPasswordForm = (ForgetPasswordForm) target;

        if (forgetPasswordForm.getEmail().isEmpty()) {
            errors.rejectValue("email", "NotEmpty.email");
        } else if (!this.emailValidator.isValid(forgetPasswordForm.getEmail())) {
            errors.rejectValue("email", "Pattern.email");
        } else if (userService.getUserByEmail(forgetPasswordForm.getEmail()) == null) {
            errors.rejectValue("email", "NotFound.email");
        }
    }
}
