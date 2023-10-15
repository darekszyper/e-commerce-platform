package com.example.ecommerceplatform.controller;

import com.example.ecommerceplatform.controller.UserController;
import com.example.ecommerceplatform.model.User;
import com.example.ecommerceplatform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        // Arrange
        Long userId = 1L;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        // Act and Assert
        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        // Arrange
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUser() throws Exception {
        // Arrange
        User user = new User();
        user.setId(1L);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // Act and Assert
        mockMvc.perform(post("/api/users")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        Long userId = 1L;

        // Act and Assert
        mockMvc.perform(delete("/api/users/{id}", userId))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(userId);
    }
}
