package com.joyldp.urlservice.service;

import org.springframework.stereotype.Service;

import com.joyldp.urlservice.entity.AvailableUrlEntity;
import com.joyldp.urlservice.repository.AvailableUrlRepository;

@Service
public class AvailableUrlService {

    private final AvailableUrlRepository availableUrlRepository;

    public AvailableUrlService(AvailableUrlRepository availableUrlRepository) {
        this.availableUrlRepository = availableUrlRepository;
    }

    public AvailableUrlEntity createOne(AvailableUrlEntity availableUrl) {
        return this.availableUrlRepository.save(availableUrl);
    }

}
