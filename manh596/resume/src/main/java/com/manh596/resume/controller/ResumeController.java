package com.manh596.resume.controller;

import com.manh596.resume.model.ContactForm;
import com.manh596.resume.service.EducationService;
import com.manh596.resume.service.ExperienceService;
import com.manh596.resume.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AllArgsConstructor
@Controller
public class ResumeController {

    private EducationService educationService;
    private ExperienceService experienceService;
    private SkillService skillService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("educations", educationService.getEducations());
        model.addAttribute("skills", skillService.getSkills());
        model.addAttribute("experiences", experienceService.getExperiences());
        model.addAttribute("contactForm", new ContactForm());

        return "index";
    }

    @PostMapping("/contactForm")
    public String getContact(@ModelAttribute ContactForm contactForm){
        System.out.println(contactForm);
        return "index";
    }
}