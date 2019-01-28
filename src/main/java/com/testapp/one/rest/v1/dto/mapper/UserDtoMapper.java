package com.testapp.one.rest.v1.dto.mapper;

import com.testapp.one.domain.AppUser;
import com.testapp.one.rest.v1.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto toDto(AppUser appUser);


    AppUser toDomain(UserDto userDto);
}
