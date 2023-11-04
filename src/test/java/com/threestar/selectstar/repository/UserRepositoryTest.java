package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepo;

    @BeforeEach
    void hr(){
        System.out.println("=".repeat(100));
    }

    @Test
    @Order(1)
    void allUser(){
        List<User> list = userRepo.findAll();
        System.out.println("User 수>>"+list.size());
        list.stream().forEach(System.out::println);
    }
}