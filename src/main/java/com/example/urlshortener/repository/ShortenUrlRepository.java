package com.example.urlshortener.repository;

import com.example.urlshortener.model.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    ShortenUrl findByFullurl(String fullurl);

    ShortenUrl findByShortened(String shortened);

    List<ShortenUrl> findAllByUserId(Long userId);

    Optional<ShortenUrl> findById(Long id);

}