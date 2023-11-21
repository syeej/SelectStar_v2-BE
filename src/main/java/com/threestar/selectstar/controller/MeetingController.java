package com.threestar.selectstar.controller;

import com.threestar.selectstar.config.auth.CustomUserDetails;
import com.threestar.selectstar.config.jwt.JwtService;
import com.threestar.selectstar.domain.service.MeetingService;
import com.threestar.selectstar.domain.service.MypageService;
import com.threestar.selectstar.dto.meeting.request.AddUpdateMeetingRequest;
import com.threestar.selectstar.dto.meeting.request.FindMainPageRequest;
import com.threestar.selectstar.dto.meeting.response.FindMainPageResponse;
import com.threestar.selectstar.dto.meeting.response.FindMeetingOneResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/meeting")
public class MeetingController {
    final MeetingService meetingService;
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final MypageService mypageService;

    public MeetingController(MeetingService meetingService, MypageService mypageService) {
        this.meetingService = meetingService;
        this.mypageService = mypageService;
    }


    // 전체 조회(페이징)
    @GetMapping
    public ResponseEntity<Page<FindMainPageResponse>> meetingList(FindMainPageRequest findMainPageRequest){
        return ResponseEntity.ok()
                .body(meetingService.findMainPage(findMainPageRequest));
    }
    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<FindMeetingOneResponse> meetingDetail(@PathVariable("id") int id){
        return ResponseEntity.ok()
                .body(meetingService.findMeetingOne(id));
    }
    // 등록
    @PostMapping
    public Map<String,String> meetingAdd(@RequestBody AddUpdateMeetingRequest addUpdateMeetingRequest,@AuthenticationPrincipal CustomUserDetails userDetails){
        Map<String, String> succesMap = new HashMap<>();
        int error = 0;
        addUpdateMeetingRequest.setUserId(userDetails.getUserId());
        for (ConstraintViolation<AddUpdateMeetingRequest> addUpdateMeetingRequestConstraintViolation : validator.validate(addUpdateMeetingRequest)) {
            System.out.println(addUpdateMeetingRequestConstraintViolation);
            error = 1;
        }
        if (error==1) {
            succesMap.put("result","fail");
            return succesMap;
        }

        succesMap.put("result",meetingService.addMeeting( addUpdateMeetingRequest));
        return succesMap;
    }
    // 수정 => AddUpdateMeetingRequest 변경 불가능 한 값 생각 해야 됨...
    @PutMapping
    public Map<String,String> meetingModify(@RequestBody AddUpdateMeetingRequest addUpdateMeetingRequest,@AuthenticationPrincipal CustomUserDetails userDetails){
        Map<String, String> succesMap = new HashMap<>();
        addUpdateMeetingRequest.setUserId(userDetails.getUserId());
        succesMap.put("result",meetingService.updateMeeting(addUpdateMeetingRequest));
        return succesMap;
    }
    // 삭제
    @DeleteMapping("/{id}")
    public Map<String,String> meetingRemove(@PathVariable("id") int id){
        Map<String, String> succesMap = new HashMap<>();
        succesMap.put("result",meetingService.removeMeeting(id));
        return succesMap;
    }

}
