package com.springforum.app.Modules.Posts.Controllers;

import com.springforum.app.Modules.Posts.Services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class AdminPostsController {

    @Autowired
    private PostServices postServices;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable long postId){
        postServices.deletePostId(postId);

        return ResponseEntity.status(200).body("Seu post foi removido.");
    }

}
