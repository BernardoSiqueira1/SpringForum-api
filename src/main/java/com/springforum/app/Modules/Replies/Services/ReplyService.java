package com.springforum.app.Modules.Replies.Services;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Posts.Repositories.PostRepository;
import com.springforum.app.Modules.Replies.Adapters.ReplyAdapter;
import com.springforum.app.Modules.Replies.DTOs.NewReplyDTO;
import com.springforum.app.Modules.Replies.DTOs.ReplyDetailsDTO;
import com.springforum.app.Modules.Replies.Model.Replies;
import com.springforum.app.Modules.Replies.Repository.ReplyRepository;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import com.springforum.app.Shared.Exceptions.PostNotFoundException;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createReply(long postId, NewReplyDTO newReplyDTO){
        User userQuery = userRepository.findById(newReplyDTO.replyUserId()).orElseThrow(UserNotFoundException::new);
        Post postQuery = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Replies newReply = ReplyAdapter.toReplyEntity(newReplyDTO, userQuery, postQuery);

        replyRepository.save(newReply);
    }

    public List<ReplyDetailsDTO> getPageRepliesByPost(long postId, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Replies> replyQuery = replyRepository.pageRepliesByPost(pageable, postId);

        return ReplyAdapter.toReplyDetails(replyQuery);
    }

    public List<ReplyDetailsDTO> getPageRepliesByUser(long userId, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Replies> replyQuery = replyRepository.pageRepliesByUser(pageable, userId);

        return ReplyAdapter.toReplyDetails(replyQuery);
    }

    @Transactional
    public void deleteReply(long replyId){
        replyRepository.deleteById(replyId);
    }

}
