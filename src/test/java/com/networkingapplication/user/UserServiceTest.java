package com.networkingapplication.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void registerUserIfNotExist() {
        //GIVEN
        UserDto userDto = UserTestData.initUser();

        //WHEN
        UserDto registeredUser = userService.registerUser(userDto);

        //THEN
        assertNotNull(registeredUser.getId());
    }

    @Test
    public void tryToRegisterTwoTimesTheSameUser() {
        //GIVEN
        UserDto userDto = UserTestData.initUser();

        //WHEN
        UserDto registeredUser1 = userService.registerUser(userDto);
        UserDto registeredUser2 = userService.registerUser(userDto);

        //THEN
        assertNotNull(registeredUser1.getId());
        assertEquals(registeredUser1.getId(), registeredUser2.getId());
    }

}