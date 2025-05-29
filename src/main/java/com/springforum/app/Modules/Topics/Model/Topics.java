package com.springforum.app.Modules.Topics.Model;

import com.springforum.app.Modules.Posts.Models.Post;
import com.springforum.app.Modules.Topics.DTOs.NewTopicDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long topicId;

    @Column(nullable = false, unique = true)
    private String topicName;

    @JoinColumn
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Post> relatedPosts;

    public Topics(String topicName){
        this.topicName = topicName;
    }
}
