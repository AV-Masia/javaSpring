package com.example.sweater.demo.service.impl;

import com.example.sweater.demo.email.EmailService;
import com.example.sweater.demo.model.Role;
import com.example.sweater.demo.model.User;
import com.example.sweater.demo.model.form.RegistrationForm;
import com.example.sweater.demo.repository.MovieRepository;
import com.example.sweater.demo.repository.UserRepository;
import com.example.sweater.demo.security.CryptConfiguration;
import com.example.sweater.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CryptConfiguration cryptConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void createUserFromRegistrationForm(RegistrationForm registrationForm) {
        User user = User.builder()
                .firstName(registrationForm.getFirstName())
                .lastName(registrationForm.getLastName())
                .email(registrationForm.getEmail())
                .password(cryptConfiguration.passwordEncoder().encode(registrationForm.getPassword()))
                .role(Role.USER)
                .build();
            userRepository.save(user);
        try {
            emailService.sendRegister(
                    registrationForm.getFirstName() + " " + registrationForm.getLastName(),
                    registrationForm.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Create user from userDTO, user= " + user);
    }

    @Override
    public User updateUserPassword(String userEmail) {
        User user = getUserByEmail(userEmail);
        user.setPassword(generateRandomPassword());
        userRepository.updateUserPassword(user.getId(), cryptConfiguration.passwordEncoder().encode(user.getPassword()));
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, RegistrationForm registrationForm) {
        User user = getUserById(id);
        user.setFirstName(registrationForm.getFirstName());
        user.setLastName(registrationForm.getLastName());
        user.setEmail(registrationForm.getEmail());
        if (!registrationForm.getPassword().isEmpty()){
            user.setPassword(cryptConfiguration.passwordEncoder().encode(registrationForm.getPassword()));
        }
        userRepository.save(user);
    }

    public static String generateRandomPassword() {
        int len = 10;
        return RandomStringUtils.randomAlphanumeric(len);
    }

}
