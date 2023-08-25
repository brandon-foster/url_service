package com.joyldp.urlservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joyldp.urlservice.entity.UrlEntity;
import com.joyldp.urlservice.service.UrlService;
import com.joyldp.urlservice.service.HashService;

@Slf4j
@RestController
@RequestMapping("/api")
public class UrlRestController {
    
    private final UrlService urlService;

    public UrlRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlEntity> createUrl(@RequestParam(name = "originalUrl") String originalUrl,
                                            @RequestParam(name = "customAlias", required = false) String customAlias,
                                            @RequestParam(name = "username", required = false) String username,
                                            @RequestParam(name = "expireDate", required = false) String expireDate) {
        log.info("originalUrl: {}", originalUrl);
        final UrlEntity urlEntity = urlService.createOne(originalUrl);
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
}
