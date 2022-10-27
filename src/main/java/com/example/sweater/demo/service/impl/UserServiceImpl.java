package com.example.sweater.demo.service.impl;

import com.example.sweater.demo.model.Role;
import com.example.sweater.demo.model.User;
import com.example.sweater.demo.model.dto.UserDTO;
import com.example.sweater.demo.repository.UserRepository;
import com.example.sweater.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    @Override
    public void createUserFromUserDTO(UserDTO userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        log.info("Create user from userDTO, user= " + user);
    }
}
