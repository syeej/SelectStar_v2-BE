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

    @Transactional
    @Order(2)
    @Test
    void 회원가입_테스트(){
        //Given
        User insertUser = User.builder()
            .name("testid")
            .password("qwer1234")
            .email("test@gmail.com")
            .nickname("test")
            .location1("경기도")
            .joinDate(Date.valueOf(LocalDate.now()))
            .interestLanguage("")
            .interestJob("_4_")
            .interestFramework("_2_")
            .build();

        // When
        userRepository.save(insertUser);

        // Then
        User saveUser = (User)userRepository.findByName("testid").orElse(null); // 아이디
        assertThat(saveUser).isNotNull();  // 사용자가 저장되었는지 확인
    }

    @Transactional
    @Order(3)
    @Test
    void 로그인_테스트() {
        //Given
        String name = "testid";
        String password = "qwer1234";
        User insertUser = User.builder()
            .name("testid")
            .password("qwer1234")
            .email("test@gmail.com")
            .nickname("test")
            .location1("경기도")
            .joinDate(Date.valueOf(LocalDate.now()))
            .interestLanguage("")
            .interestJob("_4_")
            .interestFramework("_2_")
            .build();
        userRepository.save(insertUser);

        // When
        Optional<User> userOptional = userRepository.findByNameAndPassword(name, password);  // 아이디와 비밀번호 일치하는지 확인

        // Then
        assertThat(userOptional.isPresent()).isTrue();
    }

    @Transactional
    @Order(4)
    @Test
    void 닉네임으로_회원_조회(){
        // Given
        String searchNickname = "test";
        User insertUser1 = User.builder()
            .name("testid")
            .password("qwer1234")
            .email("test@gmail.com")
            .nickname("test")
            .location1("경기도")
            .joinDate(Date.valueOf(LocalDate.now()))
            .interestLanguage("")
            .interestJob("_4_")
            .interestFramework("_2_")
            .build();
        userRepository.save(insertUser1);
        User insertUser2 = User.builder()
            .name("idtestid")
            .password("qwer1234")
            .email("test@gmail.com")
            .nickname("test")
            .location1("경기도")
            .joinDate(Date.valueOf(LocalDate.now()))
            .interestLanguage("")
            .interestJob("_4_")
            .interestFramework("_2_")
            .build();
        userRepository.save(insertUser2);

        // When
        List<User> searchResultByNickname = userRepository.findByNicknameLike("%"+searchNickname+"%");

        // Then
        assertThat(searchResultByNickname.size()).isGreaterThan(0); // 검색 결과가 존재하는지 확인
        assertThat(searchResultByNickname.get(0).getNickname()).isEqualTo("test"); // 닉네임이 일치하는지 확인
    }
    //마이페이지 - 이력관리, 개인정보 조회
    //마이페이지 - 이력관리 수정
    //마이페이지 - 개인정보 수정

}