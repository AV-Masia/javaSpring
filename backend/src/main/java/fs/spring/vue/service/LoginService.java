package fs.spring.vue.service;

import fs.spring.vue.model.form.LoginRequest;
import fs.spring.vue.model.form.LoginResponse;

public interface LoginService {

    LoginResponse doLogin(LoginRequest loginRequest);
}
