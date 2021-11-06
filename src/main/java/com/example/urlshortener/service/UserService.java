package com.example.urlshortener.service;

import com.example.urlshortener.dto.UserDto;
import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDto userDto){
        User user = userDto.userDtoToUser(userDto);
        this.userRepository.save(user);
    }

    public String getUserId(String username){
        User user = this.userRepository.findByUsername(username);
        return String.valueOf(user.getId());
    }
}
