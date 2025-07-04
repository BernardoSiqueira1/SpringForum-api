package com.springforum.app.Modules.Replies.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewReplyDTO(
        @NotNull(message = "É necessário uma resposta")
        @NotEmpty
        String replyContent,

        @NotEmpty
        @NotNull(message = "É necessário o id do usuário.")
        Long replyUserId
) {}
