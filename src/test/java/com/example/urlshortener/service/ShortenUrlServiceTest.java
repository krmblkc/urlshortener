package com.example.urlshortener.service;

import com.example.urlshortener.model.ShortenUrl;
import com.example.urlshortener.repository.ShortenUrlRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ShortenUrlServiceTest {
    private ShortenUrlRepository shortenUrlRepository;

    private ShortenUrlService shortenUrlService;

    @Before
    public void setUp() throws Exception {
        shortenUrlService = new ShortenUrlService();
        shortenUrlRepository = Mockito.mock(ShortenUrlRepository.class);
    }

    @Test
    public void whenGetUrlCalledWithFullurl_itShouldReturnString() {
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setId(5L);
        shortenUrl.setFullurl("test");
        shortenUrl.setShortened("test");
        shortenUrl.setUserId(5L);
        String fullurl = "test";

        Mockito.when(shortenUrlRepository.findByFullurl(fullurl)).thenReturn(shortenUrl);
        String result = String.valueOf(shortenUrlRepository.findByFullurl(fullurl).getId());

        Assert.assertEquals(result, String.valueOf(shortenUrl.getId()));
        Mockito.verify(shortenUrlRepository).findByFullurl(fullurl);
    }

    @Test
    public void whenGetFullurlCalledWithShortened_itShouldReturnString(){
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setId(5L);
        shortenUrl.setFullurl("test");
        shortenUrl.setShortened("test");
        shortenUrl.setUserId(5L);
        String shortened = "test";

        Mockito.when(shortenUrlRepository.findByShortened(shortened)).thenReturn(shortenUrl);
        String result = shortenUrlRepository.findByShortened(shortened).getFullurl();

        Assert.assertEquals(result,shortenUrl.getShortened());
        Mockito.verify(shortenUrlRepository).findByShortened(shortened);
    }

    @Test
    public void whenGetAllByUserIdCalledWhithUserId_itShouldReturnList(){
        ShortenUrl shortenUrl1 = new ShortenUrl();
        shortenUrl1.setId(5L);
        shortenUrl1.setFullurl("test");
        shortenUrl1.setShortened("test");
        shortenUrl1.setUserId(5L);

        ShortenUrl shortenUrl2 = new ShortenUrl();
        shortenUrl2.setId(6L);
        shortenUrl2.setFullurl("test");
        shortenUrl2.setShortened("test");
        shortenUrl2.setUserId(5L);
        List<ShortenUrl> list = new ArrayList<>();
        list.add(shortenUrl1);
        list.add(shortenUrl2);
        Long userId = 5L;

        Mockito.when(shortenUrlRepository.findAllByUserId(userId)).thenReturn(list);
        List<ShortenUrl> result = shortenUrlRepository.findAllByUserId(userId);

        Assert.assertEquals(result,list);
        Mockito.verify(shortenUrlRepository).findAllByUserId(userId);
    }

    @Test
    public void whenFindByIdCalledWithId_itShouldReturnOptional(){
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setId(5L);
        shortenUrl.setFullurl("test");
        shortenUrl.setShortened("test");
        shortenUrl.setUserId(5L);
        Long id = 5L;
        Optional<ShortenUrl> opt = Optional.of(shortenUrl);

        Mockito.when(shortenUrlRepository.findById(id)).thenReturn(opt);
        Optional<ShortenUrl> result = shortenUrlRepository.findById(id);

        Assert.assertEquals(result,opt);
        Mockito.verify(shortenUrlRepository).findById(id);
    }
}