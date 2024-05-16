package com.codeki.post.utils;

import com.codeki.post.model.Post;
import com.codeki.post.model.PostDto;
import com.codeki.post.model.UserDto;
import com.codeki.post.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostUtils {
    @Autowired
    UserClient userClient;

    public PostDto postToDto(Post post) {
        UserDto userDto = userClient.findUserById(post.getUserId());
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getImage(), userDto);

    }

    public List<PostDto> dtoToPostList(List<Post> postList) {
        return postList.stream()
                .map(post -> {
                    //por medio de feingClient llamo al servivio de usuarios para obtener el userDto
                    UserDto userDto = userClient.findUserById(post.getUserId());

                    return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getImage(), userDto);
                })
                .collect(Collectors.toList());
    }
}
