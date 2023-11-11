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
public class Apply {

    @EmbeddedId
    private ApplyID applyID;

    @ManyToOne

    @JoinColumn(name="user_id",insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="meeting_id", insertable = false, updatable = false)
    private Meeting meeting;

    private String emailAddress;
    private String snsAddress;
    private String reason;
    @CreationTimestamp
    private java.sql.Date applicationDate;
}
