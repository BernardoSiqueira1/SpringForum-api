package com.springforum.app.Modules.User.Adapters;


import com.springforum.app.Modules.User.DTOs.UserProfileDetailsDTO;
import com.springforum.app.Modules.User.Model.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserAdapters {

    public static UserProfileDetailsDTO userToUserProfileDTO(User user){
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new UserProfileDetailsDTO(user.getUserNickname(),
                user.getUserImageURL(),
                user.getUserId(),
                user.getUserCreationDate().format(dateTimeFormat)
        );
    }

}
