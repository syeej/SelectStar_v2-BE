package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Meeting;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class MeetingRepositoryTest {

    @Autowired
    MeetingRepository meetingRepo;

    @BeforeEach
    void hr(){
        System.out.println("=".repeat(100));
    }

    @Test
    @Order(1)
    void allMeeting(){
        List<Meeting> list = meetingRepo.findAll();
        System.out.println("Meeting 수 >>"+list.size());
        list.stream().forEach(System.out::println);
    }

    @Test
    @Order(2)
    void meetingOne(){
        Meeting m= meetingRepo.findById(1).get();
        System.out.println(m);
    }

}