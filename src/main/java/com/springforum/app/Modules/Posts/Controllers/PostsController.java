package com.springforum.app.Modules.Posts.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @PostMapping("")
    public ResponseEntity<?> createNewPost(){
        return null;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(){
        return null;
    }

    @GetMapping("/{postPage}")
    public ResponseEntity<?> getPostByPage(){
        return null;
    }

    @PutMapping("/edit/{postId}")
    public ResponseEntity<?> editPost(){
        return null;
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(){
        return null;
    }
}
