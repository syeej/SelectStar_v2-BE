package com.threestar.selectstar.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class ApplyID implements Serializable {
    //@Column(name="userId")
    private Integer userId;

    //@Column(name = "meetingId")
    private Integer meetingId;
}
