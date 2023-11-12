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
    //마이페이지 - 이력관리, 개인정보 조회
    @Transactional
    @Order(5)
    @Test
    void 아이디로_마이페이지_조회(){
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
                .aboutMe("한줄소개하는 중")
                .profileContent("이력관리 열심히 작성해야지")
                .interestLanguage("")
                .interestJob("_4_")
                .interestFramework("_2_")
                .build();
        userRepository.save(insertUser);

        List<User> list = userRepository.findAll();
        list.stream().forEach(System.out::println);
        System.out.println("======================");
        User user = userRepository.findById(6).get();
        //User user = userRepository.findById(1).get();
        System.out.println(user);
    }
    //마이페이지 - 이력관리 수정
    @Transactional
    @Order(6)
    @Test
    void 이력관리수정(){
        //Given
        String name = "김가영";
        String password = "qwer1234";
        User insertUser = User.builder()
                .name("testid")
                .password("qwer1234")
                .email("test11@gmail.com")
                .nickname("가영testNickname")
                .location1("경기도")
                .joinDate(Date.valueOf(LocalDate.now()))
                .aboutMe("한줄소개하는 중")
                .profileContent("이력관리 열심히 작성해야지")
                .interestLanguage("")
                .interestJob("_4_")
                .interestFramework("_2_")
                .build();
        userRepository.save(insertUser);

        List<User> list = userRepository.findAll();
        list.stream().forEach(System.out::println);
        System.out.println("======================");
        String newAboutme = "한줄소개하는 중(수정하는 중)";
        String newProfileContent = "이력관리 열심히 작성해야지(이력관리도 수정)";

        User oldUser = userRepository.findById(7).get();
        System.out.println("수정 전");
        System.out.println(oldUser);
        oldUser.setAboutMe(newAboutme);
        oldUser.setProfileContent(newProfileContent);
        System.out.println("수정 후");
        System.out.println(oldUser);
        userRepository.save(oldUser);
    }
    //마이페이지 - 개인정보 수정
    @Transactional
    @Order(7)
    @Test
    void 개인정보수정(){
        //Given
        String name = "김나영";
        String password = "qwer1234";
        User insertUser = User.builder()
                .name("김나영")
                .password("qwer1234")
                .email("test1234@gmail.com")
                .nickname("나영testNickname")
                .location1("경기도")
                .joinDate(Date.valueOf(LocalDate.now()))
                .aboutMe("김나영 한줄소개하는 중")
                .profileContent("김나영 이력관리 열심히 작성해야지")
                .interestLanguage("_1_")
                .interestJob("_1_")
                .interestFramework("_1_")
                .build();
        userRepository.save(insertUser);

        List<User> list = userRepository.findAll();
        list.stream().forEach(System.out::println);
        System.out.println("======================");
        String newPw= "qwert321";
        String newEmail = "new@gmail.com";
        String newNick = "새 별명";
        String newLocation1 = "서울";
        String newLocation2 = "제주";
        String newLang = "_1_2_3_";
        String newJob = "_1_2_3_";
        String newFW = "_1_2_3_";

        User oldUser = userRepository.findById(8).get();
        System.out.println("수정 전");
        System.out.println(oldUser);
        oldUser.setPassword(newPw);
        oldUser.setEmail(newEmail);
        oldUser.setNickname(newNick);
        oldUser.setLocation1(newLocation1);
        oldUser.setInterestLanguage(newLang);
        oldUser.setInterestJob(newJob);
        oldUser.setInterestFramework(newFW);
        oldUser.setLocation2(newLocation2);
        System.out.println("수정 후");
        System.out.println(oldUser);
        userRepository.save(oldUser);
    }

    //마이페이지-프로필 이미지 수정
    @Transactional
    @Order(8)
    @Test
    void 프로필이미지수정(){
        //Given
        String name = "김가영";
        String password = "qwer1234";
        User insertUser = User.builder()
                .name(name)
                .password("qwer1234")
                .email("test11@gmail.com")
                .nickname("가영testNickname")
                .location1("경기도")
                .joinDate(Date.valueOf(LocalDate.now()))
                .aboutMe("한줄소개하는 중")
                .profileContent("이력관리 열심히 작성해야지")
                .interestLanguage("_1_")
                .interestJob("_1_")
                .interestFramework("_1_")
                .build();
        userRepository.save(insertUser);

        List<User> list = userRepository.findAll();
        list.stream().forEach(System.out::println);
        System.out.println("======================");
        byte[] newPhoto = null;

        User oldUser = userRepository.findById(9).get();
        System.out.println("수정 전");
        System.out.println(oldUser);
        oldUser.setProfilePhoto(newPhoto);
        System.out.println("수정 후");
        System.out.println(oldUser);
        userRepository.save(oldUser);
    }
    //다른 이용자 프로필 조회
    @Transactional
    @Order(9)
    @Test
    void 다른이용자_프로필조회(){
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
                .aboutMe("한줄소개하는 중")
                .profileContent("이력관리 열심히 작성해야지")
                .interestLanguage("")
                .interestJob("_4_")
                .interestFramework("_2_")
                .build();
        userRepository.save(insertUser);

        List<User> list = userRepository.findAll();
        list.stream().forEach(System.out::println);
        System.out.println("======================");
        User user = userRepository.findById(10).get();
        System.out.println(user);

    }

}