package com.springforum.app.Modules.User.Adapters;


import com.springforum.app.Modules.User.DTOs.NewUserDTO;
import com.springforum.app.Modules.User.DTOs.UserProfileDetailsDTO;
import com.springforum.app.Modules.User.Enums.UserType;
import com.springforum.app.Modules.User.Model.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserAdapters {

    public static User toUserEntity(NewUserDTO newUserDTO, UserType userType){
        return new User(newUserDTO.userEmail(),
                newUserDTO.userName(),
                newUserDTO.userPassword(),
                newUserDTO.userImageURL(),
                userType
                );
    }

    public static UserProfileDetailsDTO userToUserProfileDTO(User user){
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new UserProfileDetailsDTO(user.getUserNickname(),
                user.getUserImageURL(),
                user.getUserId(),
                user.getUserCreationDate().format(dateTimeFormat)
        );
    }

}
