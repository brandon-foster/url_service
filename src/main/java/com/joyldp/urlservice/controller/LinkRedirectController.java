package com.joyldp.urlservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.joyldp.urlservice.entity.UrlEntity;
import com.joyldp.urlservice.service.UrlService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LinkRedirectController {

    @Value("${ui.prefix}")
    private String uiPrefix;
    private final UrlService urlService;

    public LinkRedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/l/{hash}")
    public String redirect(HttpServletRequest req, @PathVariable(name = "hash") String hash) {
        log.info("ui.prefix: {}", uiPrefix);
        final UrlEntity urlEntity = urlService.retrieveUrlByHash(hash);
        if (urlEntity == null) {
            final String notFoundUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + uiPrefix + "/m/"
                    + hash;
            log.info("notFoundUrl: {}", notFoundUrl);
            return "redirect:" + notFoundUrl;
        }
        return "redirect:" + urlEntity.getOriginalUrl();
    }

}
