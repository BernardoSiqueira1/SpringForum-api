package com.springforum.app.Modules.Authentication.Service;

import com.springforum.app.Modules.Authentication.Repository.AuthenticationRepository;
import com.springforum.app.Modules.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GetUserDetails implements UserDetailsService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return authenticationRepository.findByUserNickname(userEmail);
    }
}
