package com.springforum.app.Modules.Replies.DTOs;

public record ReplyDetailsDTO(
        long replyId,
        String replyContent,
        long userId,
        String replyUser,
        String replyDate
) {
}
