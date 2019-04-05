package com.manh596.contact.bootstrapData;

import com.manh596.contact.model.Contact;
import com.manh596.contact.model.Email;
import com.manh596.contact.model.Message;
import com.manh596.contact.service.ContactService;
import com.manh596.contact.service.EmailService;
import com.manh596.contact.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private ContactService contactService;
    private EmailService emailService;
    private MessageService messageService;

    private void initializeContactData() {
        contactService.addContact(new Contact("cnt000001", "Manh", "nguyenvanmanh596@gmail.com", "380669832312", "kharkiv"));
    }

    private void initializeEmailData() {
        emailService.sendEmail(new Email("eml000001", "guest@gmail.com", "user1", "pass1", "from1", "380930092589", "subject", new Message("msg000001", "eml00001", "usr000001", "message content", "02-22-2019", false)));
    }

    @Override
    public void run(String... args) throws Exception {
        initializeContactData();
        initializeEmailData();
        System.out.println("sdfss");
    }
}