package com.manh596.contact.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "message")
public class Message {
    private String id;
    private String emailId;
    private String userId;
    private String message;
    private String date;
    private Boolean isEdited;
}
