package com.springforum.app.Config.Security;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Posts.Repositories.PostRepository;
import com.springforum.app.Modules.Replies.Model.Replies;
import com.springforum.app.Modules.Replies.Repository.ReplyRepository;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import com.springforum.app.Shared.Exceptions.PostNotFoundException;
import com.springforum.app.Shared.Exceptions.ReplyNotFoundException;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorshipVerification {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public boolean verifyIsAccountOwner(Long userId, String authenticationUsername){
        User userQuery = userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return userQuery.getUsername().equals(authenticationUsername);
    }

    public boolean verifyIsPostOwner(Long postId, String authenticationUsername){
        Post postQuery = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return postQuery.getPostUsuario().getUsername().equals(authenticationUsername);
    }

    public boolean verifyIsReplyOwner(Long replyId, String authenticationUsername){
        Replies replyQuery = replyRepository.findById(replyId)
                .orElseThrow(ReplyNotFoundException::new);

        return replyQuery.getReplyUser().getUsername().equals(authenticationUsername);
    }
}
