package com.manh596.resume.repository.config;

import com.manh596.common.repository.mongo.config.MongoConnectionHelper;
import com.manh596.resume.model.Education;
import com.manh596.resume.model.Experience;
import com.manh596.resume.model.Skill;
import com.manh596.resume.repository.ResumeRepository;
import com.manh596.resume.repository.impl.ResumeRepositoryImpl;
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
@EnableMongoRepositories(basePackages = "com.manh596.resume.repository")
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

    @Bean("primaryEducationRepository")
    public SimpleMongoRepository<Education, String> getPrimaryEducationRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<Education, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Education>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Education.class));
        return new SimpleMongoRepository<>(info, primaryMongoTemplate());
    }

    @Bean("secondaryEducationRepository")
    public SimpleMongoRepository<Education, String> getSecondaryEducationRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<Education, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Education>) secondaryMongoTemplate().getConverter().getMappingContext().getPersistentEntity(Education.class));
        return new SimpleMongoRepository<>(info, mongoTemplate);
    }

    @Bean("educationRepository")
    public ResumeRepository<Education, String> getEducationRepository() {
        return new ResumeRepositoryImpl<>(getPrimaryEducationRepository(), getSecondaryEducationRepository());
    }

    @Bean("skillPrimary")
    public SimpleMongoRepository<Skill, String> getPrimarySkillRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<Skill, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Skill>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Skill.class));
        return new SimpleMongoRepository<>(info, primaryMongoTemplate());
    }

    @Bean("skillSecondary")
    public SimpleMongoRepository<Skill, String> getSecondarySkillRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<Skill, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Skill>) secondaryMongoTemplate().getConverter().getMappingContext().getPersistentEntity(Skill.class));
        return new SimpleMongoRepository<>(info, mongoTemplate);
    }

    @Bean("skillRepository")
    public ResumeRepository<Skill, String> getSkillRepository() {
        return new ResumeRepositoryImpl<>(getPrimarySkillRepository(), getSecondarySkillRepository());
    }

    @Bean("experiencePrimary")
    public SimpleMongoRepository<Experience, String> getPrimaryExperienceRepository() {
        MongoTemplate mongoTemplate = primaryMongoTemplate();
        MongoEntityInformation<Experience, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Experience>) mongoTemplate.getConverter().getMappingContext().getPersistentEntity(Experience.class));
        return new SimpleMongoRepository<>(info, primaryMongoTemplate());
    }

    @Bean("experienceSecondary")
    public SimpleMongoRepository<Experience, String> getSecondaryExperienceRepository() {
        MongoTemplate mongoTemplate = secondaryMongoTemplate();
        MongoEntityInformation<Experience, String> info = new MappingMongoEntityInformation<>(
                (MongoPersistentEntity<Experience>) secondaryMongoTemplate().getConverter().getMappingContext().getPersistentEntity(Experience.class));
        return new SimpleMongoRepository<>(info, mongoTemplate);
    }

    @Bean("experienceRepository")
    public ResumeRepository<Experience, String> getCategoryRepository() {
        return new ResumeRepositoryImpl<>(getPrimaryExperienceRepository(), getSecondaryExperienceRepository());
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