package com.manh596.common.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class NumberService {

    public String generateId() {
        AtomicInteger atomicInteger = new AtomicInteger(999999);
        return String.valueOf(atomicInteger.getAndIncrement());
    }
}