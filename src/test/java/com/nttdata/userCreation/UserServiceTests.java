package com.nttdata.userCreation;

import com.nttdata.DTO.UserDto;
import com.nttdata.exceptions.EmailAlreadyExistsException;
import com.nttdata.exceptions.InvalidDataException;
import com.nttdata.models.Phone;
import com.nttdata.models.User;
import com.nttdata.repository.UserRepository;
import com.nttdata.service.TokenService;
import com.nttdata.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserServiceTests {
    @InjectMocks
    private UserRepository userRepository;

    @InjectMocks
    private TokenService tokenService;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_ValidUser_ReturnsCreatedUser() throws EmailAlreadyExistsException, InvalidDataException {
        UserDto userDto = new UserDto();
        userDto.setName("Misdely Morales");
        userDto.setEmail("misdely@email.com");
        userDto.setPassword("Misdjhjsdh88");
        List<Phone> phones = new ArrayList<>();
        userDto.setPhones(phones);

        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhones(userDto.getPhones());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);

        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(tokenService.generateToken(any(User.class))).thenReturn("token123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(userDto);

        // Assert
        assertNotNull(createdUser);
        assertEquals(userDto.getName(), createdUser.getName());
        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertEquals(userDto.getPassword(), createdUser.getPassword());
        assertEquals(userDto.getPhones(), createdUser.getPhones());
        assertNotNull(createdUser.getCreated());
        assertNotNull(createdUser.getModified());
        assertNotNull(createdUser.getLastLogin());
        assertEquals("token123", createdUser.getToken());
        assertTrue(createdUser.isActive());



    }

    @Test
    void createUser_ExistingEmail_ThrowsEmailAlreadyExistsException() {
        UserDto userDto = new UserDto();
        userDto.setName("Misdely Morales");
        userDto.setEmail("misdely@email.com");
        userDto.setPassword("Misdjhjsdh88");
        userDto.setPhones(Collections.emptyList());

        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(userDto));
    }
}

