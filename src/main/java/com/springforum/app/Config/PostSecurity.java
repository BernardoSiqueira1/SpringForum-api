package com.springforum.app.Config;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Posts.Repositories.PostRepository;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostSecurity {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    public boolean isAccountOwner(Long userId, String authenticationUsername){
        User userQuery = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não foi encontrado"));

        return userQuery.getUserEmail().equals(authenticationUsername);
    }

    public boolean isPostOwner(Long postId, String authenticationUsername){
        Post postQuery = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post não foi encontrado."));

        return postQuery.getPostUsuario().getUserEmail().equals(authenticationUsername);
    }

}
