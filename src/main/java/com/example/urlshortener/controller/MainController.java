package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlDto;
import com.example.urlshortener.dto.UserDto;
import com.example.urlshortener.model.ShortenUrl;
import com.example.urlshortener.service.ShortenUrlService;
import com.example.urlshortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShortenUrlService shortenUrlService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        this.userService.addUser(userDto);
        return ResponseEntity.ok("userId: " + userService.getUserId(userDto.getUsername()));
    }

    @PostMapping("/user/{userId}/url/create")
    public ResponseEntity<String> addUrl(@RequestBody UrlDto urlDto,@PathVariable(name = "userId") Long userId) {
        this.shortenUrlService.setShortUrl(this.shortenUrlService.getRandomChars(), urlDto,userId);
        return ResponseEntity.ok(this.shortenUrlService.getUrl(urlDto.getFullurl()));
    }

    @GetMapping("/s/{shortened}")
    public void goFullUrl(HttpServletResponse response, @PathVariable(name = "shortened") String shortened) throws IOException {
      response.sendRedirect(this.shortenUrlService.getFullUrl(shortened));
    }

    @GetMapping("/user/{userId}/url/list")
    public ResponseEntity<List<ShortenUrl>> getAllByUserId(@PathVariable(name = "userId")Long userId){
        return ResponseEntity.ok(this.shortenUrlService.getAllByUserId(userId));
    }

    @GetMapping("/user/{userId}/url/detail/{id}")
    public ResponseEntity<Optional<Optional<ShortenUrl>>> getOne(@PathVariable(name = "userId")Long userId , @PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(this.shortenUrlService.findById(id));
    }

    @DeleteMapping("/user/{userId}/url/detail/{id}")
    public ResponseEntity<Long> deleteUrl(@PathVariable(name = "userId") Long userId , @PathVariable(name = "id") Long id){
        this.shortenUrlService.deleteOne(id);
        return ResponseEntity.ok(id);
    }
}
