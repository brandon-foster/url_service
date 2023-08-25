package com.joyldp.urlservice.service;

import org.springframework.stereotype.Service;

import com.joyldp.urlservice.entity.UrlEntity;
import com.joyldp.urlservice.repository.UrlRepository;

import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlEntity createOne(UrlEntity url) {
        return urlRepository.save(url);
    }

    public UrlEntity retrieveUrlByHash(String hash) {
        return urlRepository.findById(hash).orElse(null);
    }
}
