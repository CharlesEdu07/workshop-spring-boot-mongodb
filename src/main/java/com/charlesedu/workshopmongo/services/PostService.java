package com.charlesedu.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.charlesedu.workshopmongo.domain.Post;
import com.charlesedu.workshopmongo.repositories.PostRepository;
import com.charlesedu.workshopmongo.services.exceptions.DatabaseException;
import com.charlesedu.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Post insert(Post obj) {
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

}
