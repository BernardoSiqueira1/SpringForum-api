package com.springforum.app.Modules.Posts.Adapters;

import com.springforum.app.Modules.Posts.DTOs.NewPostDTO;
import com.springforum.app.Modules.Posts.DTOs.PostDetailsDTO;
import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Topics.Model.Topics;
import com.springforum.app.Modules.User.Model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public class PostAdapters {

    public static Post toPostEntity(NewPostDTO newPostDTO, User originalPoster, Topics postTopic){

        return new Post(
                newPostDTO.postTitle(),
                newPostDTO.postImageUrl(),
                newPostDTO.postDescription(),
                originalPoster,
                postTopic
        );

    }

    public static PostDetailsDTO toPostDetailsDTO(Post post){
        return new PostDetailsDTO(post.getPostId(),
                post.getPostTitle(),
                post.getPostDescription(),
                post.getPostImageUrl(),
                post.getPostUsuario().getUserId(),
                post.getPostUsuario().getUsername(),
                post.getTopics().getTopicName()
        );
    }

    public static List<PostDetailsDTO> toPostDetailsDTO(Page<Post> postPage){
        return postPage.stream().map( post -> {
            return new PostDetailsDTO(post.getPostId(),
                    post.getPostTitle(),
                    post.getPostDescription(),
                    post.getPostImageUrl(),
                    post.getPostUsuario().getUserId(),
                    post.getPostUsuario().getUsername(),
                    post.getTopics().getTopicName()
            );
        }).toList();
    }
}
