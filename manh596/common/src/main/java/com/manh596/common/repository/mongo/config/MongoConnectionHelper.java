package com.manh596.common.repository.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.Collections;
import java.util.List;

public class MongoConnectionHelper<D, K> {

    public MongoTemplate mongoTemplate(MultipleMongoProperties mongoProperties) {
        return new MongoTemplate(primaryFactory(mongoProperties.getPrimary()));
    }

    @Primary
    public MongoTemplate primaryMongoTemplate(MultipleMongoProperties mongoProperties) {
        return new MongoTemplate(primaryFactory(mongoProperties.getPrimary()));
    }

    public MongoTemplate secondaryMongoTemplate(MultipleMongoProperties mongoProperties) {
        return new MongoTemplate(secondaryFactory(mongoProperties.getSecondary()));
    }

    public SimpleMongoRepository<D, K> getPrimaryRepository(MultipleMongoProperties mongoProperties, Class modelClass) {
        MongoTemplate mongoTemplate = primaryMongoTemplate(mongoProperties);
        MongoEntityInformation<D, K> information = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<D>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(modelClass)
        );
        return new SimpleMongoRepository<>(information, mongoTemplate);
    }

    public SimpleMongoRepository<D, K> getSecondaryRepository(MultipleMongoProperties mongoProperties, Class modelClass) {
        MongoTemplate mongoTemplate = secondaryMongoTemplate(mongoProperties);
        MongoEntityInformation<D, K> information = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<D>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(modelClass)
        );
        return new SimpleMongoRepository<>(information, mongoTemplate);
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

    public SimpleMongoDbFactory getMongoDbFactory(MongoProperties mongo) {
        MongoCredential mongoCredential = MongoCredential.createCredential(mongo.getUsername(), mongo.getAuthenticationDatabase(), mongo.getPassword());
        List<MongoCredential> mongoCredentialList = Collections.singletonList(mongoCredential);
        ServerAddress serverAddress = new ServerAddress(mongo.getHost(), mongo.getPort());
        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentialList);
        return new SimpleMongoDbFactory(mongoClient,
                mongo.getDatabase());
    }
}