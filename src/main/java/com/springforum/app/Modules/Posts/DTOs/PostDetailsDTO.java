package com.springforum.app.Modules.Posts.DTOs;

import java.io.Serializable;

public record PostDetailsDTO(
        long postId,
        String postTitle,
        String postContent,
        String imageUrl,
        Long originalPosterId,
        String originalPosterName,
        String topicName
) implements Serializable {}
