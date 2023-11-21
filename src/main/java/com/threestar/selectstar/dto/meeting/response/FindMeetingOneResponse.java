package com.threestar.selectstar.dto.meeting.response;

import com.threestar.selectstar.domain.entity.Meeting;
import lombok.*;

import java.sql.Date;




@Getter
@Builder
@ToString
@AllArgsConstructor
public class FindMeetingOneResponse {
    private Integer meetingId;
    private Integer userId;
    private String userNickname;
    private String description;
    private String title;
    private int category;
    private int status;
    private Date applicationDeadline;
    private int views;
    private int recruitmentCount;
    private int applicationCount;
    private String location;
    private Date creationDate;
    private String interestLanguage;
    private String interestFramework;
    private String interestJob;
    @Setter
    private Integer loginId;

    public static FindMeetingOneResponse fromEntity(Meeting meeting, String nickname){
        return FindMeetingOneResponse.builder()
                .meetingId(meeting.getMeetingId())
                .userId(meeting.getUser().getUserId())
                .userNickname(nickname)
                .description(meeting.getDescription())
                .title(meeting.getTitle())
                .category(meeting.getCategory())
                .status(meeting.getStatus())
                .applicationDeadline(meeting.getApplicationDeadline())
                .views(meeting.getViews())
                .recruitmentCount(meeting.getRecruitmentCount())
                .applicationCount(meeting.getApplicationCount())
                .location(meeting.getLocation())
                .creationDate(meeting.getCreationDate())
                .interestLanguage(meeting.getInterestLanguage())
                .interestFramework(meeting.getInterestFramework())
                .interestJob(meeting.getInterestJob())
                .build();
    }
}

