package com.manh596.contact.service.impl;

import com.manh596.contact.model.Email;
import com.manh596.contact.model.Message;
import com.manh596.contact.repository.ContactRepository;
import com.manh596.contact.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmailServiceImpl implements EmailService {

    @Autowired
    @Qualifier("emailRepository")
    private ContactRepository repository;

    @Override
    public void sendEmail(Email email) {
        repository.save(email);
    }

    @Override
    public void removeEmail(Email email) {
        repository.delete(email.getId());
    }

    @Override
    public void updateEmail(Email toBeUpdated) {
        repository.delete(toBeUpdated.getId());
        repository.save(toBeUpdated);
    }
}