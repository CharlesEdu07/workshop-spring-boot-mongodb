package com.charlesedu.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charlesedu.workshopmongo.domain.User;
import com.charlesedu.workshopmongo.dto.UserDTO;
import com.charlesedu.workshopmongo.repositories.UserRepository;
import com.charlesedu.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public User fromDTO(UserDTO obj) {
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }

}
