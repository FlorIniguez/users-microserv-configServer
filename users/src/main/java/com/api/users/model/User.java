package com.api.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app-users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String avatar;

    //constructor sin ID para test
    public User(String name, String email, String username, String avatar) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.avatar = avatar;
    }
}
