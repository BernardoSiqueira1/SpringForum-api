package com.springforum.app.Modules.Authentication.DTOs;

public record AuthCredentialsDTO(
        String userLogin,
        String userPassword
){}
