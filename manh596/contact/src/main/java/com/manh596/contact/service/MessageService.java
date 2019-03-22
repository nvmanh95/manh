package com.manh596.contact.service;

import com.manh596.contact.api.request.SendMessageRequest;
import com.manh596.contact.model.Message;

public interface MessageService {
    void sendMessage(SendMessageRequest message);

    void updateMessage(Message message);

    void deleteMessage(Message message);
}
