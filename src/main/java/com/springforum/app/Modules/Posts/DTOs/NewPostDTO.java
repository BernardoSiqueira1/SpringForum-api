package com.springforum.app.Modules.Posts.DTOs;

public record NewPostDTO(

        String postTitle,
        String postImageUrl,
        String postDescription,
        long topicId,
        long originalPosterId

) {}
