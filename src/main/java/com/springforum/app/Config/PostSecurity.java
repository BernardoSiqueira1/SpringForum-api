package com.springforum.app.Config;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Posts.Repositories.PostRepository;
import com.springforum.app.Modules.Replies.Model.Replies;
import com.springforum.app.Modules.Replies.Repository.ReplyRepository;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import com.springforum.app.Shared.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostSecurity {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public boolean isAccountOwner(Long userId, String authenticationUsername){
        User userQuery = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMessage()));

        return userQuery.getUsername().equals(authenticationUsername);
    }

    public boolean isPostOwner(Long postId, String authenticationUsername){
        Post postQuery = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.POST_NOT_FOUND.getMessage()));

        return postQuery.getPostUsuario().getUsername().equals(authenticationUsername);
    }

    public boolean isReplyOwner(Long replyId, String authenticationUsername){
        Replies replyQuery = replyRepository.findById(replyId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.REPLY_NOT_FOUND.getMessage()));

        return replyQuery.getReplyUser().getUsername().equals(authenticationUsername);
    }
}
