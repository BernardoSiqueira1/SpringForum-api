package com.springforum.app.Modules.User.Model;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.User.DTOs.NewUserDTO;
import com.springforum.app.Modules.User.Enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(nullable = false, unique = true)
    private String userNickname;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = true)
    private String userImageURL;

    @Column(nullable = false)
    private LocalDateTime userCreationDate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Column(nullable = false)
    private boolean userEnabled;

    @OneToMany(mappedBy = "postUsuario", fetch = FetchType.LAZY)
    List<Post> userPosts;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.userType == UserType.USER){
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        else if (this.userType == UserType.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername(){
        return this.userNickname;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public boolean isEnabled() { return this.userEnabled; }

    public User(String userEmail, String userNickname, String userPassword, String userImageURL, UserType userType){
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userImageURL = userImageURL;
        this.userCreationDate = LocalDateTime.now();
        this.userType = userType;
        this.userEnabled = true;
    }

}
