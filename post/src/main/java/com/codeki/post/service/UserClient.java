package com.codeki.post.service;

import com.codeki.post.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "users-service")
public interface UserClient {
@GetMapping("/users/get")
    List<UserDto> getAllUsers();
@GetMapping("/users/{id}")
    UserDto findUserById(@PathVariable("id") Long id);
}
