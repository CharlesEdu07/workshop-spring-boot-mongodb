package com.charlesedu.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charlesedu.workshopmongo.domain.Post;
import com.charlesedu.workshopmongo.domain.User;
import com.charlesedu.workshopmongo.dto.AuthorDTO;
import com.charlesedu.workshopmongo.dto.CommentDTO;
import com.charlesedu.workshopmongo.repositories.PostRepository;
import com.charlesedu.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User user1 = new User(null, "Gato Felix", "gatofelix@gmail.com");
        User user2 = new User(null, "Jaspion", "jaspion@gmail.com");
        User user3 = new User(null, "Jiraiya", "jiraiya@gmail.com");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Post post1 = new Post(null, Instant.parse("2021-09-01T19:53:07Z"), "Partiu viagem",
                "Vou viajar para São Paulo. Abraços!", new AuthorDTO(user1));

        Post post2 = new Post(null, Instant.parse("2021-09-03T19:53:07Z"), "Bom dia", "Acordei feliz hoje!",
                new AuthorDTO(user1));

        CommentDTO comment1 = new CommentDTO("Boa viagem mano!", Instant.parse("2021-09-01T19:53:07Z"),
                new AuthorDTO(user2));

        CommentDTO comment2 = new CommentDTO("Aproveite!", Instant.parse("2021-09-02T19:53:07Z"),
                new AuthorDTO(user3));

        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2021-09-03T19:53:07Z"),
                new AuthorDTO(user2));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        user1.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(user1);
    }

}
