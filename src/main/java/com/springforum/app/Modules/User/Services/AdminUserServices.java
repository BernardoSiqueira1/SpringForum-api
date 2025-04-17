package com.springforum.app.Modules.User.Services;

import com.springforum.app.Modules.User.Enums.UserType;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServices {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void promoteUserToAdmin(long userId){
        User userQuery = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuário não foi encontrado."));
        userQuery.setUserType(UserType.ADMIN);

        userRepository.save(userQuery);
    }

    @Transactional
    public void suspendUser(long userId){
        User userQuery = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuário não foi encontrado."));
        userQuery.setUserEnabled(false);

        userRepository.save(userQuery);
    }


}
