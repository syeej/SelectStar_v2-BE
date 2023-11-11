package com.threestar.selectstar.controller;

import com.threestar.selectstar.domain.service.MeetingService;
import com.threestar.selectstar.dto.meeting.request.AddMeetingRequest;
import com.threestar.selectstar.dto.meeting.response.FindMeetingOneResponse;
import com.threestar.selectstar.dto.meeting.response.GetMainPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = {"http://localhost:8080","http://localhost:63342"})
@RestController
@RequestMapping("/comment")
public class CommentController {
    final
    MeetingService meetingService;

    public CommentController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public ResponseEntity<?> meetingList(@RequestParam(value = "page",required = false, defaultValue = "0") Integer page, @RequestParam(value = "size",required = false,defaultValue = "10") Integer size, @RequestParam(value = "order",required = false,defaultValue = "desc") String desc,  @RequestParam(value = "category",required = false,defaultValue = "") String category){
        Page<GetMainPageResponse> mainPage = meetingService.findMainPage(page, size);
        return ResponseEntity.ok()
                .body(mainPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> meetingDetail(@PathVariable("id") int id){
        FindMeetingOneResponse meetingOne = meetingService.findMeetingOne(id);
        return ResponseEntity.ok()
                .body(meetingOne);
    }
    @PostMapping
    public ResponseEntity<?> meetingAdd(@RequestBody AddMeetingRequest addMeetingRequest){
        System.out.println(addMeetingRequest);
        String msg = "";
        if (meetingService.addMeeting(addMeetingRequest)){
            msg = "등록 성공";
        } else {
            msg = "둥록 실패";
        }
        return ResponseEntity.ok()
                .body(msg);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> meetingModify(@PathVariable("id") int id, @RequestBody AddMeetingRequest addMeetingRequest){
        String msg = "";
        addMeetingRequest.setMeetingId(id);
        if (meetingService.updateMeeting(addMeetingRequest)){
            msg = "수정 성공";
        } else {
            msg = "수정 실패";
        }
        return ResponseEntity.ok()
                .body(msg);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> meetingRemove(@PathVariable("id") int id){
        String msg = "";
        if (meetingService.removeMeeting(id)){
            msg = "삭제 성공";
        } else {
            msg = "삭제 실패";
        }
        return ResponseEntity.ok()
                .body(msg);
    }


}
