package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void db_init(){

    }

    @Transactional
    @Order(1)
    @Test
    void 데이터_저장후_조회(){
        // given
        User user1 = User.builder()

                .aboutMe("gd")
                .email("starc13@naver.com")
                .interestFramework("_0_")
                .interestJob("_0_")
                .interestLanguage("_0_")
                .joinDate(Date.valueOf(LocalDate.now()))
                .location1("서울특별시")
                .location2("광주광역시")
                .name("sungsu")
                .nickname("성수닉")
                .password("12345")
                .profileContent("ㅎㅇㅎㅇㅎㅇㅎ")
                .build();
        userRepository.save(user1);
        //when
        User user2 = userRepository.findById(1).get();
        //then
        assertThat(user1).isEqualTo(user2);
    }
}