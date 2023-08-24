package com.joyldp.urlservice.service;

import org.springframework.stereotype.Service;

import com.joyldp.urlservice.entity.UrlEntity;
import com.joyldp.urlservice.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository availableUrlRepository;

    public UrlService(UrlRepository availableUrlRepository) {
        this.availableUrlRepository = availableUrlRepository;
    }

    public UrlEntity createOne(UrlEntity availableUrl) {
        return this.availableUrlRepository.save(availableUrl);
    }

}
