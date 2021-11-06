package com.example.urlshortener.service;

import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
        userRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    public void whenGetUserIdCalledWithUsername_itShouldReturnString(){
        User user = new User();
        user.setId(5L);
        user.setUsername("test");
        user.setPassword("test");
        String username = "test";

        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);
        String result = String.valueOf(userRepository.findByUsername(username).getId());

        Assert.assertEquals(result,String.valueOf(user.getId()));
        Mockito.verify(userRepository).findByUsername(username);
    }
}