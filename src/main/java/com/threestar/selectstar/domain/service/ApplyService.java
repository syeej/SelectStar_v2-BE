package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.repository.ApplyRepository;
import com.threestar.selectstar.repository.MeetingRepository;
import com.threestar.selectstar.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplyService {
    final ApplyRepository applyRepository;
    final UserRepository userRepository;
    final MeetingRepository meetingRepository;

    public ApplyService(ApplyRepository applyRepository, UserRepository userRepository, MeetingRepository meetingRepository) {
        this.applyRepository = applyRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
    }
    // 지원 하기

    // 지원 했는지 여부

    // 내가 지원한 글 들

    // 이 글에 지원한 지원자
    @Transactional
    public boolean removeMeeting(int  id){
        try {
            meetingRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
