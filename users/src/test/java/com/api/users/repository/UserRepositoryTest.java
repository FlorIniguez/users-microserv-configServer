package com.api.users.repository;

import com.api.users.model.User;
import com.api.users.model.UserDto;
import com.api.users.service.UserService;
import com.api.users.utils.UserUtils;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserUtils userUtils;
    @Autowired
    UserService userService;
    private User userTest;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userTest = new User("Flor", "flor@gmail.com", "FlorTest", "Aca iria avatar");
    }

    @Test
    void saveUserTest() {
        //user1 va a ser el user del setup
        User user1 = userRepository.save(userTest);
        assertThat(user1).isNotNull();
        assertThat(user1.getName()).isEqualTo("Flor");
    }

    @Test
    void userFindByIdTest() {
        //guardo un usuario
        userRepository.save(userTest);
        // Recupera el usuario por su ID
        User userBD = userRepository.findById(userTest.getId()).get();
        assertThat(userBD).isNotNull();
        assertThat(userBD.getName()).isEqualTo(userTest.getName());
        assertThat(userBD.getEmail()).isEqualTo(userTest.getEmail());
    }

    @Test
    void getAllUsersTest() {


    }

    @Test
    void updateUserTest() {
        userRepository.save(userTest);

        User user1 = userRepository.findById(userTest.getId()).get();

        user1.setName("Name");
        user1.setUsername("Username");

        User userUpdated = userRepository.save(user1);

        assertThat(userUpdated.getName()).isEqualTo("Name");
        assertThat(userUpdated.getUsername()).isEqualTo("Username");

    }

    @Test
    void deleteUserTest() {
        userRepository.save(userTest);
        userRepository.deleteById(userTest.getId());

        Optional<User> deletedUser = userRepository.findById(userTest.getId());

        assertTrue(deletedUser.isEmpty());

    }


}