package com.threestar.selectstar.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@ToString
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingId;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    private String title;
    private int category;
    private int status;
    private java.sql.Date applicationDeadline;
    @ColumnDefault("0")
    private int views;
    private int recruitmentCount;
    private int applicationCount;
    private String location;
    private String description;
    @CreationTimestamp
    private java.sql.Date creationDate;
    private String interestLanguage;
    private String interestFramework;
    private String interestJob;
    @ColumnDefault("0")
    private int isDelete; // 0:삭제X 1:삭제
}
