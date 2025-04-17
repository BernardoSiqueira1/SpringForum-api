package com.springforum.app.Modules.User.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EditUserCredentialsDTO(
        @NotNull
        @NotEmpty
        String newUserEmail,

        @NotNull
        @NotEmpty
        String newUserPassword
)
{}
