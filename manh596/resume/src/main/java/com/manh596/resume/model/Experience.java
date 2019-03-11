package com.manh596.resume.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "experience")
public class Experience {

    @Id
    private String id;
    private String workedAt;
    private String address;
    private String startedTime;
    private String endingTime;
    private Boolean isWorkingAt;
}