package com.joyldp.urlservice.controller;

import com.joyldp.urlservice.dto.UrlDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joyldp.urlservice.entity.UrlEntity;
import com.joyldp.urlservice.service.UrlService;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shorturl")
public class UrlRestController {
    
    private final UrlService urlService;

    public UrlRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlEntity> createUrl(@RequestBody UrlDto urlDto) {
        log.info("originalUrl: {}", urlDto.getOriginalUrl());
        final UrlEntity urlEntity = urlService.createOne(urlDto.getOriginalUrl());
        return ResponseEntity.ok().body(urlEntity);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<UrlEntity> provideUrlEntityByHash(@PathVariable(name = "hash") String hash) {
        final UrlEntity urlEntity = urlService.retrieveUrlByHash(hash);
        if (urlEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(urlEntity);
    }

    @GetMapping
    public List<UrlEntity> retrieveAll() {
        return urlService.retrieveAll();
    }
}
