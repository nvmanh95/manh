package com.manh596.resume.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contactForm")
public class ContactForm {
    @Id
    private String id;
    private String contactName;
    private String contactMail;
    private String contactNumber;
    private String contactSubject;
    private String contactMessage;
}
