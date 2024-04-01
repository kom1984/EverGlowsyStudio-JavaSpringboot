package com.everglowsy.projectfinal.service;

import com.everglowsy.projectfinal.model.Role;
import com.everglowsy.projectfinal.model.UserModel;
import com.everglowsy.projectfinal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserModel user = userRepository.findByEmail(username);

        if (user == null) throw new UsernameNotFoundException("User not found with this email: " + username);

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getText()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);


    }



    public UserModel findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public UserModel getUserById(Long id)
    {

        return userRepository.findById(id).get();
    }
    public void saveUser(UserModel saveuser)
    {
        userRepository.save(saveuser);
    }
   /* public Optional<UserModel> findById(Long userId) {
        return userRepository.findById(userId);
    }*/

    public List<UserModel> getAllUsers() {
        return userRepository.findAllByRole(Role.USER);
    }

    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
