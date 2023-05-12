package com.nttdata.userCreation;

import com.nttdata.controllers.UserController;
import com.nttdata.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUSer_ValidUser_Returns200AndUser(){

    }
}
