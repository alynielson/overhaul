package com.testapp.one.rest.v1.dto.mapper;

import com.testapp.one.domain.User;
import com.testapp.one.rest.v1.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto toDto(User user);

    @Mappings(
            @Mapping(target = "id", ignore = true)
    )
    User toDomain(UserDto userDto);
}
