package com.example.sweater.demo.service;

import com.example.sweater.demo.model.User;
import com.example.sweater.demo.model.form.RegistrationForm;

public interface UserService {

    User getUserByEmail (String email);

    User getUserById(Long id);

    void createUserFromRegistrationForm(RegistrationForm registrationForm);

    User updateUserPassword(String email);

    void deleteUserById(Long id);

    void updateUser(RegistrationForm registrationForm);
}
