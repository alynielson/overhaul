package com.testapp.one.repository;

import com.testapp.one.domain.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
