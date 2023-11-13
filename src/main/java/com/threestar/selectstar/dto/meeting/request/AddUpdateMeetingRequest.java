package com.threestar.selectstar.dto.meeting.request;

import com.threestar.selectstar.domain.entity.Meeting;
import com.threestar.selectstar.domain.entity.User;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateMeetingRequest {
    private Integer meetingId;
    private Integer userId;
    private String title;
    private Integer category;
    private Integer status;
    private Date applicationDeadline;
    private Integer views;
    private Integer recruitmentCount;
    private Integer applicationCount;
    private String location;
    private String description;
    private Date creationDate;
    private String interestLanguage;
    private String interestFramework;
    private String interestJob;


    public static Meeting toEntity(AddUpdateMeetingRequest addUpdateMeetingRequest, User user) {
        return Meeting.builder()
                .meetingId(addUpdateMeetingRequest.getMeetingId())
                .user(user) // User 엔터티로 변환
                .title(addUpdateMeetingRequest.getTitle())
                .category(addUpdateMeetingRequest.getCategory())
                .status(addUpdateMeetingRequest.getStatus())
                .applicationDeadline(addUpdateMeetingRequest.getApplicationDeadline())
                .views(addUpdateMeetingRequest.getViews())
                .recruitmentCount(addUpdateMeetingRequest.getRecruitmentCount())
                .applicationCount(addUpdateMeetingRequest.getApplicationCount())
                .location(addUpdateMeetingRequest.getLocation())
                .description(addUpdateMeetingRequest.getDescription())
                .creationDate(addUpdateMeetingRequest.getCreationDate())
                .interestLanguage(addUpdateMeetingRequest.getInterestLanguage())
                .interestFramework(addUpdateMeetingRequest.getInterestFramework())
                .interestJob(addUpdateMeetingRequest.getInterestJob())
                .build();
    }
    // 업데이트 수정 => https://jojoldu.tistory.com/415

    public void meetingRequestUpdate(AddUpdateMeetingRequest add, User user) {
        this.meetingId = add.getMeetingId();
    }
}



