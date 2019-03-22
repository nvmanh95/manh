package com.manh596.contact.service.impl;

import com.manh596.contact.api.request.SendMessageRequest;
import com.manh596.contact.model.Message;
import com.manh596.contact.repository.ContactRepository;
import com.manh596.contact.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MessageServiceImpl implements MessageService {

    @Autowired
    @Qualifier("messageRepository")
    private ContactRepository repository;

    @Override
    public void sendMessage(SendMessageRequest message) {
        Message newMessage = new Message();

        repository.save(message);
    }

    @Override
    public void updateMessage(Message message) {
        deleteMessage(message);
        repository.save(message);
    }

    @Override
    public void deleteMessage(Message message) {
        repository.delete(message.getId());
    }
}