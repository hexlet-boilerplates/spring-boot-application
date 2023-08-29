package io.hexlet.blog.component;

import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import io.hexlet.blog.model.User;
import io.hexlet.blog.repository.UserRepository;
import io.hexlet.blog.util.ModelGenerator;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ModelGenerator modelGenerator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";
        var userData = new User();
        userData.setEmail(email);
        userData.setPassword("qwerty");
        userRepository.save(userData);

        var user2 = Instancio.of(modelGenerator.getUserModel()).create();
        userRepository.save(user2);
    }
}
