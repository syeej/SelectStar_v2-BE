package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    //마이페이지(이력관리, 개인정보) 조회
    @Test
    void getUserInfo(){
        System.out.println("서비스 테스트");
        User user = userService.getUserInfoByUserId(1);
        System.out.println(user);
    }

}