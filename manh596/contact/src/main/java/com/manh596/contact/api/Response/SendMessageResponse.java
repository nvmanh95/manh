package com.manh596.contact.api.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponse {
    private String message;
    private String userId;
    private String emailId;
}