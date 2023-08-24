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
        final String generatedHash = hashGenerator.generateHash();
        log.info("generatedHash: {}", generatedHash);
        if (urlRepository.existsById(generatedHash)) {
            log.info("hash exists: {}", generatedHash);
            return "UNAVAI";
        }
        return generatedHash;
    }

}
