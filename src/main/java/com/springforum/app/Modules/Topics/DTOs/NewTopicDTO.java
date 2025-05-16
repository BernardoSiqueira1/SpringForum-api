package com.springforum.app.Modules.Topics.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewTopicDTO(
        @NotNull
        @NotEmpty
        @Size(min = 6, max = 20)
        String topicName
){}
