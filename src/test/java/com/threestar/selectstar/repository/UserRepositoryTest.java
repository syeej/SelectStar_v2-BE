package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.AddUserRequest;

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

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Order(1)
    @Test
    void 회원가입_테스트(){
        //Given
        AddUserRequest insertUser = AddUserRequest.builder()
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
        User savedUser = userRepository.save(insertUser.toEntity());

        // Then
        assertThat(savedUser).isNotNull();  // 사용자가 저장되었는지 확인
    }

    @Transactional
    @Order(2)
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
    @Order(3)
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

}