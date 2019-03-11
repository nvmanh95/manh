package com.manh596.resume.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "education")
public class Education {
    @Id
    private String id;
    private String university;
    private String level;
    private String address;
    private String dateStarted;
    private String dateGraduated;
}