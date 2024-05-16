package com.codeki.post.service;

import com.codeki.post.exceptions.ResourceNotFoundException;
import com.codeki.post.model.Post;
import com.codeki.post.model.PostDto;
import com.codeki.post.model.UserDto;
import com.codeki.post.repository.PostRepository;
import com.codeki.post.utils.PostUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    private UserClient userClient;
    @Autowired
    PostUtils postUtils;

    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postUtils.dtoToPostList(postList);
    }

    public Post createPost(Long userId, Post post) {
        post.setUserId(userId);
        return postRepository.save(post);
    }

    public PostDto getPostWithUser(Long postId) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return postUtils.postToDto(post);
    }


    public Post updatePost(Long id, Post post) {
        Post searchedPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        //actualizar post
        searchedPost.setTitle((post.getTitle()));
        searchedPost.setContent(post.getContent());
        searchedPost.setImage((post.getImage()));
        //lo guardo
        return postRepository.save(searchedPost);

    }

    public void deletePost(Long id) throws ResourceNotFoundException {
        if (id != null) {
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
            postRepository.deleteById(post.getId());
        } else {
            throw new IllegalArgumentException("The given id must not be null");
        }
    }
}
