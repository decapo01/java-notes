package com.reachengine.notes.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultLocalDateTimeProvider implements LocalDateTimeProvider {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
