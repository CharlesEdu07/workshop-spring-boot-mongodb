package com.charlesedu.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.charlesedu.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
