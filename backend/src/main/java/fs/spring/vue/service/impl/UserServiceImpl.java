package fs.spring.vue.service.impl;

import fs.spring.vue.email.EmailService;
import fs.spring.vue.model.Role;
import fs.spring.vue.model.User;
import fs.spring.vue.model.form.RegistrationForm;
import fs.spring.vue.repository.UserRepository;
import fs.spring.vue.security.CryptConfiguration;
import fs.spring.vue.service.UserService;
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
        userRepository.updateUserPassword(user.getId(),
                cryptConfiguration.passwordEncoder().encode(user.getPassword()));
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(RegistrationForm registrationForm) {
        User user = userRepository.getById(registrationForm.getId());
        user.setFirstName(registrationForm.getFirstName());
        user.setLastName(registrationForm.getLastName());
        user.setEmail(registrationForm.getEmail());
        if (!registrationForm.getPassword().isEmpty()){
            user.setPassword(cryptConfiguration.passwordEncoder().encode(registrationForm.getPassword()));
        }
        userRepository.save(user);
    }

    private String generateRandomPassword() {
        int len = 10;
        return RandomStringUtils.randomAlphanumeric(len);
    }

}
