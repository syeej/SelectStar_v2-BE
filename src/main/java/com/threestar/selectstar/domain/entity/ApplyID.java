package com.threestar.selectstar.domain.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class ApplyID implements Serializable {
    //@Column(name="userId")
    private int userId;

    //@Column(name = "meetingId")
    private int meetingId;
}
