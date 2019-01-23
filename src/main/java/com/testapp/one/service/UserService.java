package com.testapp.one.service;

import com.testapp.one.domain.User;
import com.testapp.one.repository.UserRepository;
import com.testapp.one.service.exception.DataConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNew(User user) {
        findUserByEmail(user.getEmail());
        user.setId(null);
        return userRepository.save(user);
    }

    private void findUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DataConflictException("Email " + email + " is already in use.");
        }
    }
}
