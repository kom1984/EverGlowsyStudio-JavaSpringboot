package com.everglowsy.projectfinal.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.everglowsy.projectfinal.model.Role;
import com.everglowsy.projectfinal.model.UserModel;
import com.everglowsy.projectfinal.repository.UserRepository;
import com.everglowsy.projectfinal.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthService authService; // AuthService depends on Mocked UserRepository and PasswordEncoder
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize Mockito annotations
    }
    @Test
    void testCreateNewUser() {
       UserModel user = new UserModel("email", "password", Role.USER);
       when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
       authService.createNewUser(user);
       verify(userRepository, times(1)).save(user);
       assertEquals("encodedPassword", user.getPassword());
       assertEquals(Role.USER, user.getRole());
    }
}
