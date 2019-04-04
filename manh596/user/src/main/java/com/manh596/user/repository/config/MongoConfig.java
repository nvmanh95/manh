package com.manh596.user.repository.config;

import com.manh596.common.repository.mongo.config.MongoConnectionHelper;
import com.manh596.user.model.User;
import com.manh596.user.repository.UserRepository;
import com.manh596.user.repository.impl.UserRepositoryImpl;
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
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.manh596.user.repository")
public class MongoConfig {

    private final MultipleMongoProperties mongoProperties;
    private MongoConnectionHelper connectionHelper;

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

    @Bean("primaryUserRepository")
    public SimpleMongoRepository<User, String> getPrimaryUserRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<User, String> information = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<User>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(User.class)
        );
        return new SimpleMongoRepository<>(information, primaryMongoTemplate());
    }

    @Bean("secondaryUserRepository")
    public SimpleMongoRepository<User, String> getSecondaryUserRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<User, String> information = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<User>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(User.class)
        );
        return new SimpleMongoRepository<>(information, secondaryMongoTemplate());
    }

    @Bean("userRepository")
    public UserRepository<User, String> getUserRepository() {
        return new UserRepositoryImpl<>(getPrimaryUserRepository(), getSecondaryUserRepository());
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(final MongoProperties mongo) {
        return connectionHelper.getMongoDbFactory(mongo);
    }

    @Bean
    public MongoDbFactory secondaryFactory(final MongoProperties mongo) {
        return connectionHelper.getMongoDbFactory(mongo);
    }
}