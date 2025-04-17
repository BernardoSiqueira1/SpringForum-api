package com.springforum.app.Config;

import com.springforum.app.Modules.Authentication.Service.TokenService;
import com.springforum.app.Modules.User.Repository.UserRepository;
import com.springforum.app.Modules.User.Services.UserServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenSubject = this.verifyAuthentication(request);
        UserDetails userQuery = userRepository.loadByUserNickname(tokenSubject);

        var authenticationToken = new UsernamePasswordAuthenticationToken(userQuery, null, userQuery.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String verifyAuthentication(HttpServletRequest request){
        String userToken = request.getHeader("Authorization");

        String tokenSubject = tokenService.validateToken(userToken);

        return tokenSubject;
    }

}
