package com.threestar.selectstar.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class ApplyID implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "meeting_id")
    private Integer meetingId;
}
