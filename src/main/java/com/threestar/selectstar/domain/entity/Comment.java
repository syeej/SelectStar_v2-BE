package com.threestar.selectstar.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@ToString
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="meeting_id")
    private Meeting meeting;
    private String content;
    private int deleted; // 0:삭제X 1:삭제
    @CreationTimestamp
    private java.sql.Date creationDate;
}
