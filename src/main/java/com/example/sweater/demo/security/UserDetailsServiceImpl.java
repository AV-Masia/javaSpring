package com.example.sweater.demo.security;

import com.example.sweater.demo.model.User;
import com.example.sweater.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            System.out.println("new UsernameNotFoundException(\"none exist: \")" + email);
            return new UsernameNotFoundException("none exist: " + email);
        });

        return SecurityUser.fromUser(user);
//        return Optional.of(userRepository.findByEmail(username).get()).stream()
//                .map(SecurityUser::fromUser)
//                .findFirst().get();
    }
}
