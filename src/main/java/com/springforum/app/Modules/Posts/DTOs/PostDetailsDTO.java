package com.springforum.app.Modules.Posts.DTOs;

public record PostDetailsDTO(
        long postId,
        String postTitle,
        String postContent,
        String imageUrl,
        Long originalPosterId,
        String originalPosterName,
        String topicName
) {}
