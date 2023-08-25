package com.joyldp.urlservice.service;

import org.springframework.stereotype.Service;

import com.joyldp.urlservice.component.HashGenerator;
import com.joyldp.urlservice.repository.UrlRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HashService {

    private final UrlRepository urlRepository;
    private final HashGenerator hashGenerator;

    public HashService(UrlRepository urlRepository, HashGenerator hashGenerator) {
        this.urlRepository = urlRepository;
        this.hashGenerator = hashGenerator;
    }

    public String provideHash() {
        String generatedHash = hashGenerator.generateHash();
        while (urlRepository.existsById(generatedHash)) {
            log.info("hash exists: {}", generatedHash);
            generatedHash = hashGenerator.generateHash();
        }
        return generatedHash;
    }

}
