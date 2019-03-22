package com.manh596.contact.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "email" )
public class Email {
    @Id
    private String id;
    private String email;
    private String userName;
    private String password;
    private String from;
    private String contactNumber;
    private String contactSubject;
    private Message contactMessage;
}