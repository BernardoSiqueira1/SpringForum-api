package com.springforum.app.Modules.Posts.Models;

import com.springforum.app.Modules.Topics.Model.Topics;
import com.springforum.app.Modules.User.Model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column(nullable = false)
    private String postImageUrl;

    @Column(nullable = false)
    private String postDescription;

    @Column(nullable = false)
    private String postTags;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    Topics topics;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    User postUsuario;

}
