package com.manh596.resume.repository.config;

import com.manh596.common.repository.mongo.config.MongoConnectionHelper;
import com.manh596.resume.model.Education;
import com.manh596.resume.model.Experience;
import com.manh596.resume.model.Skill;
import com.manh596.resume.repository.ResumeRepository;
import com.manh596.resume.repository.impl.ResumeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MultipleMongoPropertiesImpl.class)
@EnableMongoRepositories(basePackages = "com.manh596.resume.repository")
public class MongoConfig {

    private final MultipleMongoPropertiesImpl mongoProperties;
    @Autowired
    private MongoConnectionHelper<Education, String> connectionHelper;
    @Autowired
    private MongoConnectionHelper<Skill, String> skillConnectionHelper;
    @Autowired
    private MongoConnectionHelper<Experience, String> experienceConnectionHelper;

    @Bean("educationRepository")
    public ResumeRepository<Education, String> getEducationRepository() {
        return new ResumeRepositoryImpl<>(connectionHelper.getPrimaryRepository(mongoProperties, Education.class), connectionHelper.getSecondaryRepository(mongoProperties, Education.class));
    }

    @Bean("skillRepository")
    public ResumeRepository<Skill, String> getSkillRepository() {
        return new ResumeRepositoryImpl<>(skillConnectionHelper.getPrimaryRepository(mongoProperties, Skill.class), skillConnectionHelper.getSecondaryRepository(mongoProperties, Skill.class));
    }

    @Bean("experienceRepository")
    public ResumeRepository<Experience, String> getCategoryRepository() {
        return new ResumeRepositoryImpl<>(experienceConnectionHelper.getPrimaryRepository(mongoProperties, Experience.class), experienceConnectionHelper.getSecondaryRepository(mongoProperties, Experience.class));
    }
}