package com.threestar.selectstar.controller;

import com.threestar.selectstar.domain.service.MeetingService;
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




}
