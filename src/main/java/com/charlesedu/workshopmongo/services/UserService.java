package com.charlesedu.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.charlesedu.workshopmongo.domain.User;
import com.charlesedu.workshopmongo.dto.UserDTO;
import com.charlesedu.workshopmongo.repositories.UserRepository;
import com.charlesedu.workshopmongo.services.exceptions.DatabaseException;
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

    public void delete(String id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ObjectNotFoundException(id);
            }
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(User obj) {
        try {
            Optional<User> entity = repository.findById(obj.getId());

            User newObj = entity.get();
    
            updateData(newObj, obj);
    
            return repository.save(newObj);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(obj.getId());
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO obj) {
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }

}
