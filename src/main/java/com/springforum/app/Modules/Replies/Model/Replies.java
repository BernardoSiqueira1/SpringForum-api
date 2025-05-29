package com.springforum.app.Modules.Replies.Model;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.User.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Replies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long replyId;

    @Column(nullable = false)
    private String replyContent;

    @Column
    private LocalDateTime timeOfReply;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    User replyUser;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

    public Replies(String replyContent, User replyUser, Post post){
        this.replyContent = replyContent;
        this.replyUser = replyUser;
        this.post = post;
        this.timeOfReply = LocalDateTime.now();
    }
}
