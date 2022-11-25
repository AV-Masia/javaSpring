package com.example.sweater.demo.security;

import com.example.sweater.demo.model.form.RegistrationForm;
import com.example.sweater.demo.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@ConfigurationProperties(prefix = "validation")
public class RegistrationValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationForm registrationForm = (RegistrationForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.email");

        if (!this.emailValidator.isValid(registrationForm.getEmail())){
            errors.rejectValue("email", "Pattern.email");
        } else if (userService.getUserByEmail(registrationForm.getEmail()) != null && registrationForm.getId() == null) {
            errors.rejectValue("email", "Duplicate.email");
        }

        if (registrationForm.getId() == null || !registrationForm.getPassword().isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.password");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.confirmPassword");
        }

        if (!errors.hasErrors()) {
            if (!registrationForm.getConfirmPassword().equals(registrationForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.confirmPassword");
            }
        }
    }
}
