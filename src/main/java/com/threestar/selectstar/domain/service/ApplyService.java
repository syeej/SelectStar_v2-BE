package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.ApplyID;
import com.threestar.selectstar.dto.apply.request.ApplyRequest;
import com.threestar.selectstar.dto.apply.response.FindApplyByMeetingIdResponse;
import com.threestar.selectstar.dto.apply.response.FindApplyByUserIdResponse;
import com.threestar.selectstar.repository.ApplyRepository;
import com.threestar.selectstar.repository.MeetingRepository;
import com.threestar.selectstar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    @Transactional
    public String addApply(ApplyRequest applyRequest) {
        try {
            applyRepository.save(ApplyRequest.toEntity(applyRequest,
                    new ApplyID(userRepository.findById(applyRequest.getUserId()).orElseThrow(IllegalArgumentException::new),
                            meetingRepository.findById(applyRequest.getMeetingId()).orElseThrow(IllegalArgumentException::new))));
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    // 지원 확인
    public boolean checkApply(int userId, int meetingId) {
        return applyRepository.existsByApplyID_User_UserIdIsAndApplyID_Meeting_MeetingIdIs(userId, meetingId);
        }

    // 내가 지원한 글들
    public List<FindApplyByUserIdResponse> findApplyByUserId(int userId) {
        return applyRepository.findByApplyID_User_UserIdIs(userId)
                .stream().map(FindApplyByUserIdResponse::fromEntity)
                .collect(Collectors.toList());

    }
    // 글에서 지원한 사람
    public List<FindApplyByMeetingIdResponse> findApplyByMeetingId(int meetingId) {
        return applyRepository.findByApplyID_Meeting_MeetingIdIs(meetingId)
                .stream().map(FindApplyByMeetingIdResponse::fromEntity)
                .collect(Collectors.toList());
    }
}


