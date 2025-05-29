package com.springforum.app.Modules.Posts.Repositories;

import com.springforum.app.Modules.Posts.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p from Post p WHERE p.topics.topicName = :topicName")
    Page<Post> pagePostsByTopic(Pageable pageable, @Param("topicName") String topicName);

}
