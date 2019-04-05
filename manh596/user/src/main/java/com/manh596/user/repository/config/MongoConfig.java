package com.manh596.user.repository.config;

import com.manh596.common.repository.mongo.config.MongoConnectionHelper;
import com.manh596.user.model.User;
import com.manh596.user.repository.UserRepository;
import com.manh596.user.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MultipleMongoPropertiesImpl.class)
@EnableMongoRepositories(basePackages = "com.manh596.user.repository")
public class MongoConfig {

    private final MultipleMongoPropertiesImpl mongoProperties;
    private MongoConnectionHelper<User, String> mongoConnectionHelper = new MongoConnectionHelper<>();

    @Bean("userRepository")
    public UserRepository<User, String> getUserRepository() {
        return new UserRepositoryImpl<>(mongoConnectionHelper.getPrimaryRepository(mongoProperties, User.class), mongoConnectionHelper.getSecondaryRepository(mongoProperties, User.class));
    }
}