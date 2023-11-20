package com.threestar.selectstar.controller;

import com.threestar.selectstar.domain.service.MeetingService;
import com.threestar.selectstar.domain.service.MypageService;
import com.threestar.selectstar.dto.mypage.*;
import com.threestar.selectstar.dto.mypage.request.UpdateMyInfoRequest;
import com.threestar.selectstar.dto.mypage.response.GetMyApplyingListResponse;
import com.threestar.selectstar.dto.mypage.response.GetMyInfoResponse;
import com.threestar.selectstar.dto.mypage.response.GetMyMeetingListResponse;
import com.threestar.selectstar.exception.MeetingNotFoundException;
import com.threestar.selectstar.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Controller
public class MypageController {

    private final MypageService mypageService;
    private final MeetingService meetingService;

    //마이페이지-이력관리 조회
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
    public Map<String, String> updateMyProfile(@PathVariable int id, @RequestBody UpdateMyInfoRequest userReq){
        Map<String, String> map = new HashMap<>();
        //String res = mypageService.updateMyProfileInfo(id, userReq);
        map.put("result", mypageService.updateMyProfileInfo(id, userReq));
        log.info("update myProfileInfo res>> "+map.get("result"));
        if(map.get("result").equals("success")){
            return map;
        }else{
            return null;
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
    //내가 작성한 글 목록 조회
    @GetMapping("/users/mymeeting/{id}")
    @ResponseBody
    public ResponseEntity<?> getMyMeeingList(@PathVariable int id){
        List<GetMyMeetingListResponse> res = meetingService.getMyMeetingList(id);
        log.info("get mymeeting res >> "+res);
        if(res == null){
            throw new MeetingNotFoundException("글이 없습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    //내가 작성한 글 목록 카테고리별/모집상태별 조회
    //@GetMapping(value = "/users/mymeetingfilter/{id}", produces = "application/json; charset=utf-8")
    @GetMapping(value = "/users/mymeetingfilter/{id}")
    public ResponseEntity<?> getMyMeetingListByFilter(@PathVariable int id,
                                                      @RequestParam(name = "category", required = false) String strCategory,
                                                      @RequestParam(name="status", required = false) String strStatus){
        List<GetMyMeetingListResponse> res = meetingService.getMyMeetingListByFilter(id, strCategory, strStatus);
        //log.info("get mymeetinglist by filter res >>"+res);
        if(res == null){
            throw new MeetingNotFoundException("글이 없습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
    //내가 신청한 글 목록 조회
    @GetMapping("/users/myapplying/{id}")
    @ResponseBody
    public ResponseEntity<?> getMyApplyingList(@PathVariable int id){
        List<GetMyApplyingListResponse> res = meetingService.getMyApplyingList(id);
        log.info("get applying res >>"+res);
        if(res == null){
            throw new MeetingNotFoundException("글이 없습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    //내가 신청한 글 목록 카테고리별/모집상태별 조회
    @GetMapping(value = "/users/myapplyingfilter/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> getMyApplyingListByFilterr(@PathVariable int id,
                                                        @RequestParam(name = "category", required = false) String strCategory,
                                                        @RequestParam(name="status", required = false) String strStatus){
        List<GetMyApplyingListResponse> res = meetingService.getMyAppyingListByFilter(id, strCategory,strStatus);
        log.info("get applying filter res >>"+res);
        if(res == null){
            throw new MeetingNotFoundException("글이 없습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
    //프로필 이미지 수정
    @PutMapping(value = "/users/setting/img/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> updateMyImg(@PathVariable int id, @RequestPart(name = "profilePhoto") MultipartFile file){
        //log.info("file check  >>"+file);
        UserImgFileDTO filedto = new UserImgFileDTO(file);
        String res = mypageService.updateMyProfileImg(id, filedto);
        //log.info("update myProfileInfo res>> "+res);
        if(res.equals("success")){
            return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
        }else{
            throw new UserNotFoundException(res);
        }
    }
}
