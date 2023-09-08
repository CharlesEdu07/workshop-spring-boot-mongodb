package com.charlesedu.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charlesedu.workshopmongo.domain.User;
import com.charlesedu.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User user1 = new User(null, "Gato Felix", "gatofelix@gmail.com");
        User user2 = new User(null, "Jaspion", "jaspion@gmail.com");
        User user3 = new User(null, "Jiraiya", "jiraiya@gmail.com");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));
    }
    
}
