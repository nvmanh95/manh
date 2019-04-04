package com.manh596.resume.service.impl;

import com.manh596.resume.model.Experience;
import com.manh596.resume.repository.ResumeRepository;
import com.manh596.resume.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    @Qualifier("experienceRepository")
    private ResumeRepository<Experience, String> repository;

    @Override
    public List<Experience> getExperiences() {
        return repository.getAll();
    }

    @Override
    public Optional<Experience> getById(String id) {
        return repository.getById(id);
    }

    @Override
    public void delete(Experience idToBeDeleted) {
        repository.delete(idToBeDeleted);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void add(Experience experience) {
        repository.save(experience);
    }

    @Override
    public void addAll(List<Experience> experiences) {
repository.saveAll(experiences);
    }

    @Override
    public void update(String toBeUpdated, Experience experience) {
        repository.update(toBeUpdated, experience);
    }
}