package com.api.users.service;

import com.api.users.exceptions.ResourcedNotFoundException;
import com.api.users.model.User;
import com.api.users.model.UserDto;
import com.api.users.repository.UserRepository;
import com.api.users.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${fakeUsers.url}")
    private String URL_USERS;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserUtils userUtils;
    private final RestTemplate restTemplate;


    //--- HAGO FETCH Y SAVE DE LOS USERS FAKES ---
    public void fetchAndSaveUsers() {
        UserDto[] userDtos = restTemplate.getForObject(URL_USERS, UserDto[].class);

        if (userDtos != null) {
            for (UserDto userDto : userDtos) {
                User user = userUtils.dtoToUser(userDto);
                userRepository.save(user);
            }
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userUtils.userMapperDtoList(userList);
    }

    public UserDto findUserById(Long id) throws ResourcedNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("User", "id", id));
        return userUtils.convertedUserDto(user);
    }
    public List<User>findUserByName(String name) {
        List<User> userList = userRepository.findAll();
        return userUtils.detectUserByName(userList,name);
    }
    public List<User> findUserByUsername(String userName) {
        List<User> userList = userRepository.findAll();
        return userUtils.detectUserByUsername(userList,userName);
    }

    public Optional<User> createUser(User user) {
        return Optional.of(userRepository.save(user));

    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("User", "id", id));
        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAvatar(user.getAvatar());
        return  userRepository.save(existingUser);
    }

    public void deleteUser(Long id) throws ResourcedNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourcedNotFoundException("User", "Id", id));
        userRepository.deleteById(user.getId());
    }



}



