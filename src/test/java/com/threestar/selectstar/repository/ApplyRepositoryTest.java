package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Apply;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class ApplyRepositoryTest {

    @Autowired
    ApplyRepository applyRepo;

    @BeforeEach
    void hr(){
        System.out.println("=".repeat(100));
    }

    @Test
    @Order(1)
    void one(){
        long applycnt = applyRepo.count();
        System.out.println("Apply 수 >>"+applycnt);
        //list.stream().forEach(System.out::println);
    }
    @Test
    @Order(2)
    void list(){
        List<Apply> list = applyRepo.findAll();
        list.stream().forEach(System.out::println);
    }


}