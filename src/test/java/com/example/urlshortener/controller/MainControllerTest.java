package com.example.urlshortener.controller;

import com.example.urlshortener.UrlshortenerApplication;
import com.example.urlshortener.dto.UrlDto;
import com.example.urlshortener.dto.UserDto;
import com.example.urlshortener.service.ShortenUrlService;
import com.example.urlshortener.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UrlshortenerApplication.class)
@WebAppConfiguration
public class MainControllerTest {
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonProcessingException, JsonParseException , IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,clazz);
    }

    @Test
    public void whenAddUserCalledWithUserDto_itShouldReturn200() throws Exception {
        String uri = "/user/signup";
        UserDto userDto = new UserDto();
        userDto.setUsername("test2");
        userDto.setPassword("test2");
        String inputJson = mapToJson(userDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    public void whenAddUrlCalledWithUrlDtoAndUserId_itShouldReturn200() throws Exception{
        Long userId = 1L;
        String uri = "/user/" + userId + "/url/create";
        UrlDto urlDto = new UrlDto();
        urlDto.setFullurl("test2");
        urlDto.setUserId(userId);
        String inputJson = mapToJson(urlDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    public void whenGetAllByUserIdCalledWithUserId_itShouldReturn200() throws Exception{
        Long userId = 1L;
        String uri = "/user/" + userId + "/url/list";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    public void whenDeleteOneCalledWithId_itShouldReturn200() throws Exception{
        Long userId = 1L;
        Long id = 3L;
        String uri = "/user/" + userId + "/url/detail/" + id;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    public void whenGetOneCalledById_itShouldReturn200() throws Exception{
        Long userId = 1L;
        Long id = 4L;
        String uri = "/user/" + userId + "/url/detail/" + id;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }
}