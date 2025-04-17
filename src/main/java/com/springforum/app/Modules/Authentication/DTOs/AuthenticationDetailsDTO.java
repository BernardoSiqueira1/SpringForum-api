package com.springforum.app.Modules.Authentication.DTOs;

import com.springforum.app.Modules.User.Enums.UserType;

public record AuthenticationDetailsDTO(
        Long userId,
        String username,
        String email,
        String authToken
) {}
