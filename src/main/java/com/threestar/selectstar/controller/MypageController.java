package com.threestar.selectstar.controller;

import com.threestar.selectstar.domain.service.MypageService;
import com.threestar.selectstar.dto.mypage.GetMyInfoResponse;
import com.threestar.selectstar.dto.mypage.UpdateMyInfoRequest;
import com.threestar.selectstar.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MypageController {

    @Autowired
    private final MypageService mypageService;

    //마이페이지-이력관리 조회
    @GetMapping("/users/profile/{id}")
    @ResponseBody
    public ResponseEntity<GetMyInfoResponse> getMyProfileInfo(@PathVariable("id") int id){
        GetMyInfoResponse res = mypageService.getMyProfileInfo(id);
        log.info("res >>"+res);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //마이페이지-이력관리 수정
    @PatchMapping("/users/profile/{id}")
    @ResponseBody
    public ResponseEntity<?> updateMyProfile(@PathVariable int id, @RequestBody UpdateMyInfoRequest userReq){
        String res = mypageService.updateMyProfileInfo(id, userReq);
        log.info("update myProfileInfo res>> "+res);
        if(res.equals("success")){
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        }else{
            throw new UserNotFoundException(res);
        }
    }

    //마이페이지-개인정보 조회
    @GetMapping("/users/setting/{id}")
    @ResponseBody
    public ResponseEntity<?> getMyInfo(@PathVariable int id){
        GetMyInfoResponse res = mypageService.getMyInfo(id);
        log.info("get myInfo res>> "+res);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //마이페이지-개인정보 수정
    @PutMapping("/users/setting/{id}")
    @ResponseBody
    public ResponseEntity<?> updateMyInfo(@PathVariable int id, @RequestBody UpdateMyInfoRequest req){
        String res = mypageService.updateMyInfo(id, req);
        log.info("update myProfileInfo res>> "+res);
        if(res.equals("success")){
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        }else{
            throw new UserNotFoundException(res);
        }
    }
}
