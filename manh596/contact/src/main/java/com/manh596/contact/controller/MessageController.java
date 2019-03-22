package com.manh596.contact.controller;

import com.manh596.contact.api.Response.SendMessageResponse;
import com.manh596.contact.api.request.SendMessageRequest;
import com.manh596.contact.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/{emailId}")
    public void sendMessage(@RequestBody SendMessageRequest messageForm) {
        messageService.sendMessage(messageForm);
    }
}