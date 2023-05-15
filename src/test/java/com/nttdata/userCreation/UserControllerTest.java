package com.nttdata.userCreation;

import com.nttdata.DTO.UserDto;
import com.nttdata.controllers.UserController;
import com.nttdata.exceptions.EmailAlreadyExistsException;
import com.nttdata.exceptions.InvalidDataException;
import com.nttdata.models.User;
import com.nttdata.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUSer_ValidUser_Returns200AndUser() throws InvalidDataException, EmailAlreadyExistsException {
        UserDto userDto = new UserDto();
        userDto.setName("Misdely Morales");
        userDto.setEmail("misdely@email.com");
        userDto.setPassword("Misdjhjsdh88");
        userDto.setPhones(Collections.emptyList());

        User user = new User();
        user.setId(1L);
        user.setName("Misdely Morales");
        user.setEmail("misdely@email.com");
        user.setPassword("Misdjhjsdh88");
        user.setPhones(Collections.emptyList());

        when(userService.createUser(userDto)).thenReturn(user);

        ResponseEntity<?> response = userController.createUser(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void createUser_ExistingEmail_Returns400AndErrorMessage() throws InvalidDataException, EmailAlreadyExistsException {
        UserDto userDto = new UserDto();
        userDto.setName("Misdely Morales");
        userDto.setEmail("misdely@email.com");
        userDto.setPassword("Misdjhjsdh88");
        userDto.setPhones(Collections.emptyList());

        when(userService.createUser(userDto)).thenThrow(EmailAlreadyExistsException.class);

        ResponseEntity<?> response = userController.createUser(userDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"mensaje\": \"El correo ya registrado\"}", response.getBody());
    }
}

