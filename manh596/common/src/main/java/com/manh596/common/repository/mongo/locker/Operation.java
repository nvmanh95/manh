package com.manh596.common.repository.mongo.locker;

public interface Operation<T>{
    T action();
}