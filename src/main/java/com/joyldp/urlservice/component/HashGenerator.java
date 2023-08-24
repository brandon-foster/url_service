package com.joyldp.urlservice.component;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class HashGenerator {
    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    public String generateHash() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomPosition = new Random().nextInt(chars.length());
            sb.append(chars.charAt(randomPosition));
        }
        return sb.toString();
    }
}
