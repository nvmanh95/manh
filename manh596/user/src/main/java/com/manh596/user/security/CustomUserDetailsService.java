package com.manh596.user.security;

import com.manh596.user.model.User;
import com.manh596.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username).orElseThrow(() -> new UsernameNotFoundException("user not exist"));
        return CustomUserDetails.create(user);
    }

    public UserDetails loadUserById(String id) {
        User user = userService.getUserById(id).orElseThrow(
                () -> new UsernameNotFoundException("user not exist")
        );

        return CustomUserDetails.create(user);
    }
}