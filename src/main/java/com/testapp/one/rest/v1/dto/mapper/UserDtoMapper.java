package com.testapp.one.rest.v1.dto.mapper;

import com.testapp.one.domain.AppUser;
import com.testapp.one.rest.v1.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserDtoMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;


    public abstract UserDto toDto(AppUser appUser);

    @Mappings(
            @Mapping(target = "password",
                    expression = "java(passwordEncoder.encode(userDto.getPassword())")
    )
    public abstract AppUser toDomain(UserDto userDto);
}
