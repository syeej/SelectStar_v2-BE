package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Comment;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commRepo;

    @BeforeEach
    void hr(){
        System.out.println("=".repeat(100));
    }
    @Test
    @Order(1)
    void one(){
        Comment c = commRepo.findById(2).get();
        System.out.println(c);
    }


}