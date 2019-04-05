package com.manh596.contact.service.impl;

import com.manh596.common.service.NumberService;
import com.manh596.contact.api.request.SendMessageRequest;
import com.manh596.contact.model.Message;
import com.manh596.contact.repository.ContactRepository;
import com.manh596.contact.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Qualifier("messageRepository")
    private ContactRepository repository;
    @Autowired
    private NumberService numberService;

    private static String MESSAGE_ID_PREFIX = "msg";


    @Override
    public void sendMessage(SendMessageRequest message) {
        Message newMessage = new Message();
        newMessage.setId(MESSAGE_ID_PREFIX + numberService.generateId());
        newMessage.setMessage(message.getMessageContent());

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