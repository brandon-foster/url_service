package com.joyldp.urlservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joyldp.urlservice.entity.AvailableUrlEntity;
import com.joyldp.urlservice.service.AvailableUrlService;
import com.joyldp.urlservice.service.HashService;

@Slf4j
@RestController
@RequestMapping("/api/create")
public class CreationController {
    
    private final AvailableUrlService availableUrlService;
    private final HashService hashService;
    
    public CreationController(AvailableUrlService availableUrlService, HashService hashService) {
        this.availableUrlService = availableUrlService;
        this.hashService = hashService;
    }

    @GetMapping
    public ResponseEntity<String> createUrl(@RequestParam(name = "originalUrl") String originalUrl,
                                            @RequestParam(name = "customAlias", required = false) String customAlias,
                                            @RequestParam(name = "username", required = false) String username,
                                            @RequestParam(name = "expireDate", required = false) String expireDate) {
        log.info("originalUrl: {}", originalUrl);
        final AvailableUrlEntity createdAvailableUrl = availableUrlService.createOne(AvailableUrlEntity.builder()
            .hash(hashService.provideHash())
            .originalUrl(originalUrl)
            .build());
        return new ResponseEntity<String>("Created AvailableUrl: " + createdAvailableUrl.toString(), HttpStatus.OK);
    }

}
