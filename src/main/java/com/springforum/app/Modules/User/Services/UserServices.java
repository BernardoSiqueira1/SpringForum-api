package com.springforum.app.Modules.User.Services;

import com.springforum.app.Modules.User.Adapters.UserAdapters;
import com.springforum.app.Modules.User.DTOs.EditUserCredentialsDTO;
import com.springforum.app.Modules.User.DTOs.NewUserDTO;
import com.springforum.app.Modules.User.DTOs.UserProfileDetailsDTO;
import com.springforum.app.Modules.User.Enums.UserType;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void createNewUser(NewUserDTO newUserDTO){
        User newForumUser = UserAdapters.toUserEntity(newUserDTO, UserType.USER);
        newForumUser.setUserPassword(passwordEncoder.encode(newForumUser.getUserPassword()));

        userRepository.save(newForumUser);
    }

    public UserProfileDetailsDTO getUserDetailsById(long userId){
        User userQuery = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário buscado não foi encontrado"));

        return UserAdapters.userToUserProfileDTO(userQuery);
    }

    @Transactional
    public void editUserCredentials(long userId, EditUserCredentialsDTO editCredentialsDTO){
        User userQuery = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário buscado não foi encontrado"));

        userQuery.setUserEmail(editCredentialsDTO.newUserEmail());
        userQuery.setUserPassword(passwordEncoder.encode(editCredentialsDTO.newUserPassword()));
    }

    @Transactional
    public void deleteUserById(long userId){
        userRepository.deleteById(userId);
    }

}
