package com.springforum.app.Modules.Posts.Repositories;

import com.springforum.app.Modules.Posts.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
