package io.hexlet.blog.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import io.hexlet.blog.model.User;
import io.hexlet.blog.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private final CustomUserDetailsService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";
        var userData = new User();
        userData.setUsername(email);
        userData.setPassword("qwerty");
        userService.createUser(userData);
    }
}
