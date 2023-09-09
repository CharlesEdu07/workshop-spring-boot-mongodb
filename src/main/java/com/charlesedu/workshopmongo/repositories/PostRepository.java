package com.charlesedu.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.charlesedu.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
