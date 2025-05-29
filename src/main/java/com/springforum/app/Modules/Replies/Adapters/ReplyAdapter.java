package com.springforum.app.Modules.Replies.Adapters;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Replies.DTOs.NewReplyDTO;
import com.springforum.app.Modules.Replies.DTOs.ReplyDetailsDTO;
import com.springforum.app.Modules.Replies.Model.Replies;
import com.springforum.app.Modules.User.Model.User;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ReplyAdapter {

    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

    public static Replies toReplyEntity(NewReplyDTO newReplyDTO, User user, Post post){
        return new Replies(
                newReplyDTO.replyContent(),
                user,
                post
        );
    }

    public static List<ReplyDetailsDTO> toReplyDetails(Page<Replies> repliesPage){
        return repliesPage.stream().map( reply -> {
            return new ReplyDetailsDTO(
                    reply.getReplyId(),
                    reply.getReplyContent(),
                    reply.getReplyUser().getUserId(),
                    reply.getReplyUser().getUserNickname(),
                    reply.getTimeOfReply().format(timeFormat)
            );
        }).toList();
    }

}
