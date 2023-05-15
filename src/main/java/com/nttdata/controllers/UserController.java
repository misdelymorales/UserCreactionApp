package com.nttdata.controllers;

import com.nttdata.DTO.UserDto;
import com.nttdata.exceptions.EmailAlreadyExistsException;
import com.nttdata.exceptions.InvalidDataException;
import com.nttdata.models.User;
import com.nttdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.createUser(userDto);
            return ResponseEntity.ok(user);
        } catch (EmailAlreadyExistsException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está registrado");
        } catch (InvalidDataException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos");
        }
    }
}

