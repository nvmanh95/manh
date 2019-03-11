package com.manh596.resume.repository;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ReadWriteLocker {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    public <T> T readLock(Operation<T> operation){
        T result = null;
        try {
            readLock.lock();
            result = operation.action();
        } finally {
            readLock.unlock();
        }
        return result;
    }

    public <T> T writeBlock(Operation<T> operation){
        T result = null;
        try {
            writeLock.lock();
            result = operation.action();
        } finally {
            writeLock.unlock();
        }
        return result;
    }
}