package com.manh596.resume.service.impl;

import com.manh596.resume.model.Skill;
import com.manh596.resume.repository.ResumeRepository;
import com.manh596.resume.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    @Qualifier("skillRepository")
    private ResumeRepository<Skill, String> repository;

    @Override
    public List<Skill> getSkills() {
        return repository.getAll();
    }

    @Override
    public Skill getById(String id) {
        return repository.getById(id);
    }

    @Override
    public void delete(String idToBeDeleted) {
        repository.delete(idToBeDeleted);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void add(Skill skill) {
        repository.save(skill);
    }

    @Override
    public void addAll(List<Skill> skills) {
        repository.saveAll(skills);
    }

    @Override
    public void update(String toBeUpdated, Skill skill) {
        repository.update(toBeUpdated, skill);
    }
}