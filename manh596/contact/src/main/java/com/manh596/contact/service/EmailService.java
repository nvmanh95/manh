package com.manh596.contact.service;

import com.manh596.contact.model.Email;
import com.manh596.contact.model.Message;

public interface EmailService {
    void sendEmail(Email email);

    void removeEmail(Email email);

    void updateEmail(Email toBeUpdated);
}
