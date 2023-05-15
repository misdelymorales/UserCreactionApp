package com.nttdata.service;

import com.nttdata.DTO.UserDto;
import com.nttdata.exceptions.EmailAlreadyExistsException;
import com.nttdata.exceptions.InvalidDataException;
import com.nttdata.models.User;
import com.nttdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  TokenService tokenService;

    public User createUser(UserDto userDto) throws EmailAlreadyExistsException, InvalidDataException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (!isValidEmail(userDto.getEmail()) || !isValidPassword(userDto.getPassword())) {
            throw new InvalidDataException();
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhones(userDto.getPhones());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(tokenService.generateToken(user));
        user.setActive(true);

        User newUser= userRepository.save(user);
        newUser.setPassword(null);
        return newUser;
    }

    private boolean isValidEmail(String email) {
        // Validar el formato del correo utilizando una expresión regular
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Validar el formato de la contraseña utilizando una expresión regular
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})[a-zA-Z\\d]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}

