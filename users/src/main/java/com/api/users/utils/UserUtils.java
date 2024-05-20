package com.api.users.utils;

import com.api.users.model.User;
import com.api.users.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtils {
    //DE LISTA USER A LISTA USER-DTO
    public List<UserDto> userMapperDtoList(List<User> userList) {
        return userList.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getAvatar()))
                .collect(Collectors.toList());
    }

    //DE 1 USER A 1 DTO
    public UserDto convertedUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getAvatar());
    }

    //DE DTO A USER
    public User dtoToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getUsername(), userDto.getEmail(), userDto.getAvatar());
    }

    public List<User> detectUserByName(List<User> userList, String name) {
       return userList.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    public List<User> detectUserByUsername(List<User> userList, String username) {
        return userList.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }
}
