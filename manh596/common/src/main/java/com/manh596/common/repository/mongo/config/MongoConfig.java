package com.manh596.common.repository.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MongoConfig {

    private final MultipleMongoProperties mongoProperties;

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(final MongoProperties mongo) {
        return getMongoDbFactory(mongo);
    }

    @Bean
    public MongoDbFactory secondaryFactory(final MongoProperties mongo) {
        return getMongoDbFactory(mongo);
    }

    private SimpleMongoDbFactory getMongoDbFactory(MongoProperties mongo) {
        MongoCredential mongoCredential = MongoCredential.createCredential(mongo.getUsername(), mongo.getAuthenticationDatabase(), mongo.getPassword());
        List<MongoCredential> mongoCredentialList = Collections.singletonList(mongoCredential);
        ServerAddress serverAddress = new ServerAddress(mongo.getHost(), mongo.getPort());
        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentialList);
        return new SimpleMongoDbFactory(mongoClient,
                mongo.getDatabase());
    }
}