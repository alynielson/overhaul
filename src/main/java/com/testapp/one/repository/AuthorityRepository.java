package com.testapp.one.repository;

import com.testapp.one.domain.Authority;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
    Optional<Authority> findByDescriptionIgnoreCase(String description);
}
