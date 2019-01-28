package com.testapp.one.rest.v1.controller;

import com.testapp.one.rest.v1.dto.UserDto;
import com.testapp.one.rest.v1.dto.mapper.UserDtoMapper;
import com.testapp.one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/app_user", produces = "application/json")
public class UserController {

    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserDtoMapper userDtoMapper,
                          UserService userService) {
        this.userDtoMapper = userDtoMapper;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(
            @RequestBody
            @Validated
            UserDto userDto
    ) {
        return userDtoMapper.toDto(
                userService.createNew(
                        userDtoMapper.toDomain(userDto)
                ));
    }

}
