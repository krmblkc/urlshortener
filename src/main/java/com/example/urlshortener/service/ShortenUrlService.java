package com.example.urlshortener.service;

import com.example.urlshortener.dto.UrlDto;
import com.example.urlshortener.model.ShortenUrl;
import com.example.urlshortener.repository.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShortenUrlService {
    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    public void setShortUrl(String randomChar, UrlDto urlDto,Long userId){
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setFullurl(urlDto.getFullurl());
        shortenUrl.setUserId(userId);
        shortenUrl.setShortened(randomChar);
        shortenUrlRepository.save(shortenUrl);
    }

    public String getRandomChars(){
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));

        return randomStr;
    }

    public String getUrl(String fullurl){
        ShortenUrl shortenUrl = this.shortenUrlRepository.findByFullurl(fullurl);
        return "id: " + shortenUrl.getId() + "\n" + "shortened: " + shortenUrl.getShortened();
    }

    public String getFullUrl(String shortened){

        return this.shortenUrlRepository.findByShortened(shortened).getFullurl();
    }

    public List<ShortenUrl> getAllByUserId(Long userId){
        return this.shortenUrlRepository.findAllByUserId(userId);
    }

    public Optional<Optional<ShortenUrl>> findById(Long id){
        return Optional.of(this.shortenUrlRepository.findById(id));
    }

    public void deleteOne(Long id){
        this.shortenUrlRepository.deleteById(id);
    }
}
