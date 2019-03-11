package com.manh596.resume.bootstrapData;

import com.manh596.resume.model.Education;
import com.manh596.resume.model.Experience;
import com.manh596.resume.model.Skill;
import com.manh596.resume.service.EducationService;
import com.manh596.resume.service.ExperienceService;
import com.manh596.resume.service.SkillService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class InitializationData implements CommandLineRunner {
    private EducationService educationService;
    private ExperienceService experienceService;
    private SkillService skillService;

    private static List<Education> initializeEducationData(){
        List<Education> educations = new ArrayList<>();

        educations.add(new Education("edu100001", "Kharkov National University of Radio Electronics", "Bachelor", "Kharkov, Ukraine", "01-09-2014", "01-09-2018"));
        educations.add(new Education("edu100002", "Kharkov National Economic University", "Master", "Kharkov, Ukraine", "01-09-2018", "01-02-2020"));
        return educations;
    }

    private static List<Skill> initializeSkills(){
        List<Skill> skills = new ArrayList<>();

        skills.add(new Skill("ski100001", "HTML, CSS, Javascript", "Can write a website in basic level", Skill.BEGINNER));
        skills.add(new Skill("ski100002", "PHP", "Can write a website in basic functionality", Skill.PRE_INTERMEDIATE));
        skills.add(new Skill("ski100003", "JAVA", "Can write a website withmost of functionality", Skill.UPPER_INTERMEDIATE));
        skills.add(new Skill("ski100004", "KOTLIN", "Basic knowledges", Skill.PRE_INTERMEDIATE));
        return skills;
    }

    private static List<Experience> initializeExperienceData(){
        List<Experience> experiences = new ArrayList<>();

        experiences.add(new Experience("exp100001", "EPAM Systems, Kharkov ", "Kharkov, Ukraine", "01-02-2019", "", true));
        return experiences;
    }

    @Override
    public void run(String... args) throws Exception {
        educationService.addAll(initializeEducationData());
        experienceService.addAll(initializeExperienceData());
        skillService.addAll(initializeSkills());

        System.out.println("Initialized all data for developing");
    }
}