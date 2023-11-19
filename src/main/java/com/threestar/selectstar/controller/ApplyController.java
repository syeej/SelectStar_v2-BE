package com.threestar.selectstar.controller;

import com.threestar.selectstar.domain.service.ApplyService;
import com.threestar.selectstar.dto.apply.request.ApplyRequest;
import com.threestar.selectstar.dto.apply.response.FindApplyByMeetingIdResponse;
import com.threestar.selectstar.dto.apply.response.FindApplyByUserIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = {"*"})
@RestController
@RequestMapping("/apply")
public class ApplyController {

    ApplyService applyService;

    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkByUserIdAndMeetingId(@RequestParam int userId, @RequestParam int meetingId){
        return ResponseEntity.ok()
                .body(applyService.checkApply(userId,meetingId));
    }
    @GetMapping("/user")
    public ResponseEntity<List<FindApplyByUserIdResponse>> applyListByUserId(@RequestParam int userId){
        return ResponseEntity.ok()
                        .body(applyService.findApplyByUserId(userId));
    }
    @GetMapping("/meeting")
    public ResponseEntity<List<FindApplyByMeetingIdResponse>> applyListByMeetingId(@RequestParam int meetingId){
        return ResponseEntity.ok()
                .body(applyService.findApplyByMeetingId(meetingId));
    }
    @PostMapping
    public Map<String,String> applyAdd(@RequestBody ApplyRequest applyRequest){
        Map<String, String> succesMap = new HashMap<>();
        succesMap.put("result",applyService.addApply(applyRequest));
        return succesMap;
    }
}
