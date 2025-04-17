package com.springforum.app.Modules.User.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotNull
        @NotEmpty
        String userName,

        @NotNull
        @NotEmpty
        String userEmail,

        @NotNull
        @NotEmpty
        String userPassword,

        @NotNull
        @NotEmpty
        String userImageURL
) {
}
