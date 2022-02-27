package com.jh.shopperweb;

import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
@Autowired private UserRepository repo;


@Test
    public void testAddNew(){
    User user = new User();
    user.setEmail("mz.rhenson@gmail.com");
    user.setPassword("12345");
    user.setFirstName("Test");
    user.setLastName("User");
    user.setUsername("testuser");

    User savedUser = repo.save(user);

    Assertions.assertThat(savedUser).isNotNull();
    Assertions.assertThat(savedUser.getUserId()).isGreaterThan(0);
}

@Test
    public void testListAll(){
    Iterable<User> users = repo.findAll();
    Assertions.assertThat(users).hasSizeGreaterThan(0);

    for (User user: users){
        System.out.println(user);
    }
}

@Test
    public void testUpdate(){
    Integer userId = 11;
    Optional<User> optionalUser = repo.findById(userId);
    User user = optionalUser.get();
    user.setUsername("Did this test work");
    repo.save(user);

    User updatedUser = repo.findById(userId).get();
    Assertions.assertThat(updatedUser.getUsername()).isEqualTo("Did this test work");
}

@Test
    public void testGet(){
    Integer userId = 2;
    Optional<User> optionalUser = repo.findById(userId);
    Assertions.assertThat(optionalUser).isPresent();
    System.out.println(optionalUser.get());
}


}
