package fs.spring.vue.service;

import fs.spring.vue.model.User;
import fs.spring.vue.model.form.RegistrationForm;

public interface UserService {

    User getUserByEmail (String email);

    User getUserById(Long id);

    void createUserFromRegistrationForm(RegistrationForm registrationForm);

    User updateUserPassword(String email);

    boolean deleteUserById(Long id);

    void updateUser(RegistrationForm registrationForm);
}
