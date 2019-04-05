package com.manh596.contact.repository.config;


import com.manh596.contact.model.Contact;
import com.manh596.contact.model.Email;
import com.manh596.contact.model.Message;
import com.manh596.contact.repository.ContactRepository;
import com.manh596.contact.repository.impl.ContactRepositoryImpl;
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
@EnableMongoRepositories(basePackages = "com.manh596.contact.repository")
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

    @Bean("emailPrimary")
    public SimpleMongoRepository<Email, String> getPrimaryEmailRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<Email, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Email>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Email.class));
        return new SimpleMongoRepository<>(info, primaryMongoTemplate());
    }

    @Bean("contactSecondary")
    public SimpleMongoRepository<Email, String> getSecondaryEmailRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<Email, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Email>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Email.class)
        );
        return new SimpleMongoRepository<>(info, secondaryMongoTemplate());
    }

    @Bean("emailRepository")
    public ContactRepository<Email, String> getEmailRepository() {
        return new ContactRepositoryImpl<>(getPrimaryEmailRepository(), getSecondaryEmailRepository());
    }

    @Bean("contactPrimary")
    public SimpleMongoRepository<Contact, String> getPrimaryContactRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<Contact, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Contact>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Contact.class));
        return new SimpleMongoRepository<>(info, primaryMongoTemplate());
    }

    @Bean("contactSecondary")
    public SimpleMongoRepository<Contact, String> getSecondaryContactRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<Contact, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Contact>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Contact.class)
        );
        return new SimpleMongoRepository<>(info, secondaryMongoTemplate());
    }

    @Bean("contactRepository")
    public ContactRepository<Contact, String> getContactRepository() {
        return new ContactRepositoryImpl<>(getPrimaryContactRepository(), getSecondaryContactRepository());
    }

    @Bean("messagePrimary")
    public SimpleMongoRepository<Message, String> getPrimaryMessageRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<Message, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Message>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Message.class));
        return new SimpleMongoRepository<>(info, primaryMongoTemplate());
    }

    @Bean("messageSecondary")
    public SimpleMongoRepository<Message, String> getSecondaryMessageRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<Message, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Message>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Message.class)
        );
        return new SimpleMongoRepository<>(info, secondaryMongoTemplate());
    }

    @Bean("messageRepository")
    public ContactRepository<Message, String> getMessageRepository() {
        return new ContactRepositoryImpl<>(getPrimaryMessageRepository(), getSecondaryMessageRepository());
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