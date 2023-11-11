package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.Meeting;
import com.threestar.selectstar.dto.MeetingDTO;
import com.threestar.selectstar.dto.meeting.request.AddMeetingRequest;
import com.threestar.selectstar.dto.meeting.request.RemoveMeetingRequest.RemoveMeetingRequest;
import com.threestar.selectstar.dto.meeting.response.FindMeetingOneResponse;
import com.threestar.selectstar.dto.meeting.response.GetMainPageResponse;
import com.threestar.selectstar.repository.ApplyRepository;
import com.threestar.selectstar.repository.CommentRepository;
import com.threestar.selectstar.repository.MeetingRepository;
import com.threestar.selectstar.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    final MeetingRepository meetingRepository;
    final UserRepository userRepository;
    final CommentRepository commentRepository;
    final ApplyRepository applyRepository;

    public MeetingService(MeetingRepository meetingRepository, UserRepository userRepository,
                          CommentRepository commentRepository, ApplyRepository applyRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.applyRepository = applyRepository;
    }

    // 미팅 페이지를 조회한다.
    public Page<GetMainPageResponse> findMainPage(int pageNum, int size) {
        // 총 페이지 랑 페이지 리스트 반환

        Pageable pageable = PageRequest.of(pageNum, size);
        Page<Meeting> byDeletedIsOrderByCreationDateDesc = meetingRepository.findByDeletedIsOrderByCreationDateDesc(0, pageable);
        return byDeletedIsOrderByCreationDateDesc.map(meeting -> GetMainPageResponse.fromEntity(meeting,
                meeting.getUser().getNickname()));
    }
    public FindMeetingOneResponse findMeetingOne(int meetingId){
        return meetingRepository.findById(meetingId).
                map(meeting -> FindMeetingOneResponse.fromEntity(meeting, meeting.getUser().getNickname()))
                .orElse(null);
    }


    @Transactional
    public boolean addMeeting(AddMeetingRequest addMeetingRequest){
        try {
            meetingRepository.save(AddMeetingRequest.toEntity
                    (addMeetingRequest,userRepository.findById
                            (addMeetingRequest.getUserId()).get()));
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    @Transactional
    public boolean updateMeeting(AddMeetingRequest addMeetingRequest){
        try {
            meetingRepository.save(AddMeetingRequest.toEntity(
                    addMeetingRequest,userRepository.findById(
                            addMeetingRequest.getUserId()).get()));
            return true;
        } catch (Exception e){
            return false;
        }
    }
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

