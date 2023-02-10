package com.michaelfmnk.jmhdemo.service;

import org.springframework.stereotype.Component;

@Component
public class SecretService {
    public void notifyAdmins() {
        long notified = 0;
        // страшная логика
        for (int i = 0; i < 10_000_000; i++) {
            notified += i;
        }
    }
}
