package com.testapp.one.rest.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Positive
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-z0-9]{3,}[@]+[a-zA-z0-9]{3,}[.]+[a-z]{2,}$")
    private String email;

    @NotBlank
    @Size(min = 10, max = 50)
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%;:\"&()_+=!]).{8,40})$")
    private String password;

    private String role;
}
