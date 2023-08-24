package com.joyldp.urlservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/create")
public class CreationController {

    @GetMapping
    public ResponseEntity<String> createUrl(@RequestParam(name = "originalUrl") String originalUrl,
                                            @RequestParam(name = "customAlias", required = false) String customAlias,
                                            @RequestParam(name = "username", required = false) String username,
                                            @RequestParam(name = "expireDate", required = false) String expireDate) {
        log.info("originalUrl: {}", originalUrl);
        return new ResponseEntity<String>("Creating url", HttpStatus.OK);
    }

}
