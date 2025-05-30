package com.springforum.app.Modules.Posts.Services;

import com.springforum.app.Modules.Posts.Adapters.PostAdapters;
import com.springforum.app.Modules.Posts.DTOs.NewPostDTO;
import com.springforum.app.Modules.Posts.DTOs.PostDetailsDTO;
import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Posts.Repositories.PostRepository;
import com.springforum.app.Modules.Topics.Model.Topics;
import com.springforum.app.Modules.Topics.Repository.TopicsRepository;
import com.springforum.app.Modules.User.Model.User;
import com.springforum.app.Modules.User.Repository.UserRepository;
import com.springforum.app.Shared.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Transactional
    public void createNewPost(NewPostDTO newPostDTO){
        User userQuery = userRepository.findById(newPostDTO.originalPosterId()).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMessage()));
        Topics topicsQuery = topicsRepository.findById(newPostDTO.topicId()).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.TOPIC_NOT_FOUND.getMessage()));

        Post newPost = PostAdapters.toPostEntity(newPostDTO, userQuery, topicsQuery);

        postRepository.save(newPost);
    }

    @Transactional
    public void changePostTopic(String topicName, long postId){
        Post postQuery = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.POST_NOT_FOUND.getMessage()));
        Topics topicsQuery = topicsRepository.findByTopicName(topicName).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.TOPIC_NOT_FOUND.getMessage()));

        postQuery.setTopics(topicsQuery);
        postRepository.save(postQuery);
    }

    public PostDetailsDTO getPostDetailsId(long postId){
        Post postQuery = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.POST_NOT_FOUND.getMessage()));

        return PostAdapters.toPostDetailsDTO(postQuery);
    }

    public List<PostDetailsDTO> getAllPostsPage(int pageNumber, String topicName){
        PageRequest queryPage = PageRequest.of((int)pageNumber, 10);
        Page<Post> postPage = postRepository.pagePostsByTopic(queryPage, topicName);

        return PostAdapters.toPostDetailsDTO(postPage);
    }

    @Transactional
    public void deletePostId(long postId){
        postRepository.deleteById(postId);
    }
}
