package com.threestar.selectstar.controller;

import com.threestar.selectstar.domain.service.MypageService;
import com.threestar.selectstar.dto.GetMyInfoResponse;
import com.threestar.selectstar.dto.UpdateMyInfoRequest;
import com.threestar.selectstar.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MypageController {

    private final MypageService mypageService;

    //마이 페이지-이력 관리 조회
    @GetMapping("/users/profile/{id}")
    @ResponseBody
    public ResponseEntity<GetMyInfoResponse> getMyProfileInfo(@PathVariable("id") int id){
        GetMyInfoResponse res = mypageService.getMyProfileInfo(id);
        log.info("res >>"+res);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //마이 페이지-이력 관리 수정
    @PatchMapping("/users/profile/{id}")
    @ResponseBody
    public ResponseEntity<?> updateMyProfile(@PathVariable int id, @RequestBody UpdateMyInfoRequest userReq){
        String result = mypageService.updateMyProfileInfo(id, userReq);
        if(result.equals("success")){
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        }else{
            throw new UserNotFoundException(result);
        }

    }
}
