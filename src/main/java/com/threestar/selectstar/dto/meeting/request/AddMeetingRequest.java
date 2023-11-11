package com.threestar.selectstar.dto.meeting.request;

import com.threestar.selectstar.domain.entity.Meeting;
import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.UserDTO;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMeetingRequest {
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


    public static Meeting toEntity(AddMeetingRequest addMeetingRequest, User user) {
        return Meeting.builder()
                .meetingId(addMeetingRequest.getMeetingId())
                .user(user) // User 엔터티로 변환
                .title(addMeetingRequest.getTitle())
                .category(addMeetingRequest.getCategory())
                .status(addMeetingRequest.getStatus())
                .applicationDeadline(addMeetingRequest.getApplicationDeadline())
                .views(addMeetingRequest.getViews())
                .recruitmentCount(addMeetingRequest.getRecruitmentCount())
                .applicationCount(addMeetingRequest.getApplicationCount())
                .location(addMeetingRequest.getLocation())
                .description(addMeetingRequest.getDescription())
                .creationDate(addMeetingRequest.getCreationDate())
                .interestLanguage(addMeetingRequest.getInterestLanguage())
                .interestFramework(addMeetingRequest.getInterestFramework())
                .interestJob(addMeetingRequest.getInterestJob())
                .build();
    }
}



