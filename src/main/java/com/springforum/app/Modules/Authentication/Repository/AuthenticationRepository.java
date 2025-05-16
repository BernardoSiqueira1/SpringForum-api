package com.springforum.app.Modules.Authentication.Repository;

import com.springforum.app.Modules.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {

    UserDetails findByUserNickname(String userNickname);

}
