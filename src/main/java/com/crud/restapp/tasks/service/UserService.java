package com.crud.restapp.tasks.service;

import com.crud.restapp.tasks.controller.UserNotFoundException;
import com.crud.restapp.tasks.domain.User;
import com.crud.restapp.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(final Long userId) {
        return repository.findById(userId);
    }

    public User saveUser(final User user) {
        return repository.save(user);
    }

    public void deleteUser(final Long userId) throws UserNotFoundException {
        try {
            repository.deleteById(userId);
        } catch (Exception e) {
            throw new UserNotFoundException("User: " + userId + " doesn't exist");
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (repository.findByUsername(username).isPresent()) {
            return repository.findByUsername(username).get();
        }
        throw new RuntimeException("User: '" + username + "' not exist!");

    }
}
