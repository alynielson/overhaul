package com.testapp.one.service;

import com.testapp.one.domain.AppUser;
import com.testapp.one.repository.UserRepository;
import com.testapp.one.rest.v1.exception.BadRequestException;
import com.testapp.one.service.exception.DataConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public AppUser createNew(AppUser appUser) {
        findUserByEmail(appUser.getEmail());
        appUser.setId(null);
        return userRepository.save(appUser);
    }

    private void findUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DataConflictException("Email " + email + " is already in use.");
        }
    }

    public void validatePassword(String password) {
        if (!password.matches("^((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%;:\"&()_+=!]).{8,40})$")) {
            throw new BadRequestException("Password must be at least 8 characters and contain all of the following:"
                    + " an uppercase letter, a lowercase letter, a digit, and a symbol matching @#$%;:\"&\\()_+=!");
        }
    }

}
