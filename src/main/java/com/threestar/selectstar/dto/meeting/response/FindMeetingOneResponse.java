package com.threestar.selectstar.dto.meeting.response;

import com.threestar.selectstar.domain.entity.Meeting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;




@Getter
@Builder
@ToString
@AllArgsConstructor
public class FindMeetingOneResponse {
    private Integer meetingId;
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

    public static FindMeetingOneResponse fromEntity(Meeting meeting, String nickname){
        return FindMeetingOneResponse.builder()
                .meetingId(meeting.getMeetingId())
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
                .interestLanguage(meeting.getInterestLanguage())
                .build();
    }
}

