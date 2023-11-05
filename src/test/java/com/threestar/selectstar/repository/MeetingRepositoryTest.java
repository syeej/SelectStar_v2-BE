package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Meeting;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
class MeetingRepositoryTest {

    @Autowired
    MeetingRepository meetingRepository;

    @BeforeEach
    @Transactional
    void db_init(){
        //given
        Meeting meeting1 = Meeting.builder()
                .meetingId(1)
                .title("테스트")
                .description("내용입니다.")
                .applicationCount(0)
                .category(0)
                .applicationDeadline(Date.valueOf("2030-12-12"))
                .recruitmentCount(10)
                .location("서울특별시")
                .creationDate(Date.valueOf("2020-12-12"))
                .interestLanguage("_0_")
                .interestFramework("_0_")
                .interestJob("_0_")
                .status(0)
                .views(0)
                .build();
        Meeting meeting2 = Meeting.builder()
                .meetingId(2)
                .title("테스트")
                .description("내용입니다.")
                .applicationCount(0)
                .category(1)
                .applicationDeadline(Date.valueOf("2030-12-12"))
                .recruitmentCount(10)
                .location("서울특별시")
                .creationDate(Date.valueOf("2020-12-12"))
                .interestLanguage("_0_")
                .interestFramework("_0_")
                .interestJob("_0_")
                .status(0)
                .views(0)
                .build();
        Meeting meeting3 = Meeting.builder()
                .meetingId(3)
                .title("테스트")
                .description("내용입니다.")
                .applicationCount(0)
                .category(2)
                .applicationDeadline(Date.valueOf("2030-12-12"))
                .recruitmentCount(10)
                .location("서울특별시")
                .creationDate(Date.valueOf("2020-12-12"))
                .interestLanguage("_0_")
                .interestFramework("_0_")
                .interestJob("_0_")
                .status(0)
                .views(0)
                .build();
        meetingRepository.save(meeting1);
        meetingRepository.save(meeting2);
        meetingRepository.save(meeting3);
    }

    @Order(1)
    @Test
    void 삭제_안된_모든미팅수(){
        //given
        //when
        List<Meeting> byDeletedIs = meetingRepository.findByDeletedIs(0);
        //then
        assertThat(byDeletedIs.size()).isEqualTo(2);
    }

    @Order(2)
    @Test
    void 삭제안된_모든미팅_수(){
        //given
        //when
        Integer count = meetingRepository.countByDeleted(0);
        //then
        assertThat(count).isEqualTo(2);
    }
    @Order(3)
    @Test
    void 삭제_안된_카테고리_미팅(){
        //given
        //when
        List<Meeting> byDeletedIsAndCategoryIs = meetingRepository.findByDeletedIsAndCategoryIs(0, 0);
        List<Meeting> byDeletedIsAndCategoryIs1 = meetingRepository.findByDeletedIsAndCategoryIs(0, 1);
        List<Meeting> byDeletedIsAndCategoryIs2 = meetingRepository.findByDeletedIsAndCategoryIs(0, 2);
        //then
        assertThat(byDeletedIsAndCategoryIs.size()).isEqualTo(1);
        assertThat(byDeletedIsAndCategoryIs1.size()).isEqualTo(1);
        assertThat(byDeletedIsAndCategoryIs2.size()).isEqualTo(1);

    }

}