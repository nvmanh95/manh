package com.manh596.contact.service.impl;

import com.manh596.contact.model.Contact;
import com.manh596.contact.repository.ContactRepository;
import com.manh596.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ContactServiceImpl implements ContactService {

    @Autowired
    @Qualifier("contactRepository")
    private ContactRepository repository;

    @Override
    public void addContact(Contact contact) {
        repository.save(contact);
    }

    @Override
    public void updateContact(Contact updatedContact) {
        repository.delete(updatedContact.getId());
        repository.save(updatedContact);
    }

    @Override
    public void removeContact(String idContactToBeRemoved) {
        repository.delete(idContactToBeRemoved);
    }

    @Override
    public Contact getContact() {
        return (Contact) repository.getAll().get(0);
    }
}