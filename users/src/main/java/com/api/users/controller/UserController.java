package com.api.users.controller;

import com.api.users.exceptions.ResourcedNotFoundException;
import com.api.users.model.User;
import com.api.users.model.UserDto;
import com.api.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;


    //PARA TRAER Y GUARDAR LOS USUARIOS
    @GetMapping("/fetch-users")
    public String fetchUsers() {
        userService.fetchAndSaveUsers();
        return "Users fetched and saved ok!";
    }

    @GetMapping("/get")
    public List<UserDto> getAllUsersController() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto findUserByIdController(@PathVariable Long id) {
            return userService.findUserById(id);
    }
    @GetMapping("/name/{name}")
    public List<User> findUserByNameController(@PathVariable String name){
        return userService.findUserByName(name);
    }
    @GetMapping("/username/{username}")
    public List<User> findUserByUsernameController(@PathVariable String userName){
        return userService.findUserByUsername(userName);
    }

    @PostMapping("/add")
    public Optional<User> createUserController(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateUserController(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserController(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return "User deleted successfully";
        } catch (ResourcedNotFoundException e) {
            System.out.println(e.getMessage());
            return "User not found";
        }

    }
}
