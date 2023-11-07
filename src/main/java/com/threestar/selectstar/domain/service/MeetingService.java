package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.Meeting;
import com.threestar.selectstar.dto.MeetingDTO;
import com.threestar.selectstar.repository.ApplyRepository;
import com.threestar.selectstar.repository.CommentRepository;
import com.threestar.selectstar.repository.MeetingRepository;
import com.threestar.selectstar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Pair<Integer, List<MeetingDTO>> GetPageMeetingDTO(int pageNum, int size) {
        // 총 페이지 랑 페이지 리스트 반환
        Pageable pageable = PageRequest.of(pageNum, size);
        Page<Meeting> byDeletedIsOrderByCreationDateDesc = meetingRepository.findByDeletedIsOrderByCreationDateDesc(0, pageable);
        List<MeetingDTO> meetingDTOList = new ArrayList<>();
        List<Meeting> content = byDeletedIsOrderByCreationDateDesc.getContent();
        for (Meeting meeting :
                content) {
            meetingDTOList.add(MeetingDTO.fromEntity(meeting));
        }
        return Pair.of(byDeletedIsOrderByCreationDateDesc.getTotalPages(), meetingDTOList);
    }
}

//    public List<MeetingDTO> getMeetingAll(){
//        List<Meeting> meetingList = meetingRepository.findAll();
//        meetingList.stream().map(MeetingDTO::fromEntity).collect(Collectors.toList());
//
