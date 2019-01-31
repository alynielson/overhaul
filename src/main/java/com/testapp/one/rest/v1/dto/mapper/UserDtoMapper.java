package com.testapp.one.rest.v1.dto.mapper;

import com.testapp.one.domain.AppUser;
import com.testapp.one.rest.v1.dto.UserDto;
import com.testapp.one.rest.v1.dto.mapper.decorator.UserDtoMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@DecoratedWith(UserDtoMapperDecorator.class)
public interface UserDtoMapper {

    UserDto toDto(AppUser appUser);

    @Mappings(
            @Mapping(target = "password",
                    ignore = true)
    )
    AppUser toDomain(UserDto userDto);
}
