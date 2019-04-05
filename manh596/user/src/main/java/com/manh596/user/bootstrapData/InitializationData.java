package com.manh596.user.bootstrapData;

import com.manh596.user.model.User;
import com.manh596.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class InitializationData implements CommandLineRunner {

    private UserService userService;

    private void initializeUsers() {
        userService.create(new User("urs000001", "manh596", "nguyenvanmanh596@gmail.com", "nvmanh95"));
    }

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        System.out.println("initialized users");
    }
}