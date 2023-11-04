package com.threestar.selectstar.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String password;
    private String email;
    private String nickname;
    private String location1;
    private String location2;
    @CreationTimestamp
    private java.sql.Date joinDate;
    private byte[] profilePhoto;
    private String aboutMe;
    private String profileContent;
    private String interestLanguage;
    private String interestFramework;
    private String interestJob;
}
