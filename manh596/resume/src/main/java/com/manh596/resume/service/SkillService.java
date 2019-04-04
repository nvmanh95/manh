package com.manh596.resume.service;

import com.manh596.resume.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getSkills();

    Optional<Skill> getById(String id);

    void delete(Skill idToBeDeleted);

    void deleteAll();

    void add(Skill skill);

    void addAll(List<Skill> skills);

    void update(String toBeUpdated, Skill skill);
}