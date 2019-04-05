package com.manh596.resume.repository.config;

import com.manh596.common.repository.mongo.config.MultipleMongoProperties;
import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoPropertiesImpl implements MultipleMongoProperties {

    private MongoProperties primary = new MongoProperties();
    private MongoProperties secondary = new MongoProperties();
}