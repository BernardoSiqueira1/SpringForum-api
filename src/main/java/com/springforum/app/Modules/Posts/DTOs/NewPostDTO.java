package com.springforum.app.Modules.Posts.DTOs;

import java.io.Serializable;

public record NewPostDTO (

        String postTitle,
        String postImageUrl,
        String postDescription,
        long topicId,
        long originalPosterId

) implements Serializable {}
