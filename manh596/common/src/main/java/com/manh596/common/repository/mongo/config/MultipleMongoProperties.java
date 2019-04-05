package com.manh596.common.repository.mongo.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;

public interface MultipleMongoProperties {
    MongoProperties getPrimary();

    MongoProperties getSecondary();
}