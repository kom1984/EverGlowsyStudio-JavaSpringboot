package com.everglowsy.projectfinal.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.everglowsy.projectfinal.model.Role;
import com.everglowsy.projectfinal.model.UserModel;

import com.everglowsy.projectfinal.repository.UserRepository;
import com.everglowsy.projectfinal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testLoadUserByUsername() {
        UserModel user = new UserModel("usertest@example.com", "password", Role.USER);
        when(userRepository.findByEmail("usertest@example.com")).thenReturn(user);
        UserDetails userDetails = userService.loadUserByUsername("usertest@example.com");
        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }
    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent@example.com");
        });
    }
    @Test
    void testSaveUser() {
        UserModel user = new UserModel("usertest@example.com", "password", Role.USER);
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void testGetAllUsers() {
        UserModel user1 = new UserModel("usertest1@example.com", "password", Role.USER);
        UserModel user2 = new UserModel("usertest2@example.com", "password", Role.USER);
        List<UserModel> users = List.of(user1, user2);
        when(userRepository.findAllByRole(Role.USER)).thenReturn(users);
        List<UserModel> retrievedUsers = userService.getAllUsers();
        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
    }
}
