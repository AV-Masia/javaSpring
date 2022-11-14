package com.example.sweater.demo.service;

import com.example.sweater.demo.model.User;
import com.example.sweater.demo.model.dto.UserDTO;

public interface UserService {

    User getUserByEmail (String email);

    void createUserFromUserDTO(UserDTO userDto);

}
