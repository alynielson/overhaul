package com.testapp.one.rest.v1.dto.mapper.decorator;

import com.testapp.one.domain.AppUser;
import com.testapp.one.repository.AuthorityRepository;
import com.testapp.one.rest.v1.dto.UserDto;
import com.testapp.one.rest.v1.dto.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

public abstract class UserDtoMapperDecorator implements UserDtoMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected AuthorityRepository authorityRepository;

    @Autowired
    @Qualifier("delegate")
    private UserDtoMapper delegate;

    @Override
    public UserDto toDto(AppUser user) {
        UserDto result = delegate.toDto(user);
        result.setAuthorities(user.getAuthorities().stream().map(a -> "ROLE_" + a.getDescription().toUpperCase())
                .collect(Collectors.toSet()));
        return result;
    }

    @Override
    public AppUser toDomain(UserDto dto) {
        AppUser result = delegate.toDomain(dto);
        result.setPassword(passwordEncoder.encode(dto.getPassword()));
        result.setAuthorities(dto.getAuthorities().stream()
                .map(a -> authorityRepository.findByDescriptionIgnoreCase(a).orElse(null))
                .collect(Collectors.toSet()));
        return result;
    }
}
