package com.personal.school.repository;

import com.personal.school.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail_Exists(){
        String email = "guilherme_scherer@outlook.com";

        Optional<User> user = userRepository.findByEmail(email);

        assertTrue(user.isPresent());
        assertEquals(email, user.get().getEmail());
    }

    @Test
    public void findByEmail_NotExists(){
        String email = "guilherme@outlook.com";

        Optional<User> user = userRepository.findByEmail(email);

        assertFalse(user.isPresent());
    }

    @Test
    public void findByEmail_Null(){
        String email = null;

        Optional<User> user = userRepository.findByEmail(email);

        assertFalse(user.isPresent());
    }

}
