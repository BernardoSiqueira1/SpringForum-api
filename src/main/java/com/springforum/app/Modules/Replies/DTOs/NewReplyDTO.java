package com.springforum.app.Modules.Replies.DTOs;

public record NewReplyDTO(
        String replyContent,
        long postId
) {}
