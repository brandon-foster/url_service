package com.joyldp.urlservice.service;

import com.joyldp.urlservice.entity.UrlEntity;
import com.joyldp.urlservice.repository.UrlRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final HashService hashService;

    public UrlService(UrlRepository urlRepository, HashService hashService) {
        this.urlRepository = urlRepository;
        this.hashService = hashService;
    }

    public UrlEntity createOne(String originalUrl) {
        final UrlEntity urlEntity = UrlEntity.builder()
                .hash(hashService.provideHash())
                .originalUrl(originalUrl)
                .build();
        return urlRepository.save(urlEntity);
    }

    public UrlEntity retrieveUrlByHash(String hash) {
        return urlRepository.findById(hash).orElse(null);
    }

    public List<UrlEntity> retrieveAll() {
        return StreamSupport.stream(urlRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
