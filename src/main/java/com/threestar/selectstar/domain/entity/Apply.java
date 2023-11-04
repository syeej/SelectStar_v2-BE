package com.threestar.selectstar.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Apply {

    @EmbeddedId
    private ApplyID applyID;

    @ManyToOne
    @JoinColumn(name="userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "meeingId", insertable = false, updatable = false)
    private Meeting meeting;

    private String emailAddress;
    private String snsAddress;
    private String reason;
    private java.sql.Date applicationDate;
}
