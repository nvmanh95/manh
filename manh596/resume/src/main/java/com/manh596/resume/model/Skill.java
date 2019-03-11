package com.manh596.resume.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skill")
public class Skill {

    public static final String BEGINNER = "beginner";
    public static final String PRE_INTERMEDIATE = "pre intermediate";
    public static final String INTERMEDIATE = "intermediate";
    public static final String UPPER_INTERMEDIATE = "upper intermediate";
    public static final String ADVANCED = "advanced";

    @Id
    private String id;
    private String skillName;
    private String skillDescription;
    private String skillState;
}