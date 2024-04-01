package com.everglowsy.projectfinal.service;

import com.everglowsy.projectfinal.model.Role;
import com.everglowsy.projectfinal.model.UserModel;
import com.everglowsy.projectfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createNewUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = Role.USER;
        user.setRole(role);
        userRepository.save(user);
    }

}
