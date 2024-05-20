package com.codeki.post.controller;

import com.codeki.post.exceptions.ResourceNotFoundException;
import com.codeki.post.model.Post;
import com.codeki.post.model.PostDto;
import com.codeki.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("")
    public List<PostDto> getAllPostController() {
        return postService.getAllPosts();
    }

    @PostMapping("/add/{userId}")
    public Post addPostController(@PathVariable Long userId, @RequestBody Post post) {
        return postService.createPost(userId, post);
    }

    //--- BUSCAR POST CON DATOS DEL USUARIO
    @GetMapping("/{postId}")
    public PostDto getPostWhitUserController(@PathVariable Long postId) {
        return postService.getPostWithUser(postId);
    }

    @PutMapping("/update/{id}")
    public Post updatePostController(@PathVariable Long id, @RequestBody Post post) {
        post.setPostDate(LocalDateTime.now());
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePostController(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return "Post deleted successfully";
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return "Post not found";

        }
    }
}
