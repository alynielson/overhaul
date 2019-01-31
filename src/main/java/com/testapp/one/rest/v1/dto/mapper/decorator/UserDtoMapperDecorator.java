package com.testapp.one.rest.v1.dto.mapper.decorator;

import com.testapp.one.domain.AppUser;
import com.testapp.one.rest.v1.dto.UserDto;
import com.testapp.one.rest.v1.dto.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserDtoMapperDecorator implements UserDtoMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("delegate")
    private UserDtoMapper delegate;

    @Override
    public AppUser toDomain(UserDto dto) {
        AppUser result = delegate.toDomain(dto);
        result.setPassword(passwordEncoder.encode(dto.getPassword()));
        return result;
    }
}
