package com.manh596.contact.service;

import com.manh596.contact.model.Contact;

public interface ContactService {

    void addContact(Contact contact);

    void updateContact(Contact updatedContact);

    void removeContact(String idContactToBeRemoved);

    Contact getContact();
}