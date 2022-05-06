package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    // inject interface instead of a impl class -> loose coupling
    private PostService postService;

    //only one constructure,can omit @autowire annotation
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post
    public ResponseEntity<PostDto>
}
