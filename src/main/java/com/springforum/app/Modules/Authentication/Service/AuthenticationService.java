package com.springforum.app.Modules.Authentication.Service;

import com.springforum.app.Modules.Authentication.DTOs.AuthenticationDetailsDTO;
import com.springforum.app.Modules.User.Model.User;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private GetUserDetails getUserDetails;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthenticationDetailsDTO authenticateUser(String login, String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login, password);

        User auth = (User) authenticationManager.authenticate(usernamePasswordAuthenticationToken).getPrincipal();

        String token = tokenService.generateToken(auth);

        return new AuthenticationDetailsDTO(auth.getUserId(), auth.getUsername(), auth.getUserEmail(), token);
    }

}
