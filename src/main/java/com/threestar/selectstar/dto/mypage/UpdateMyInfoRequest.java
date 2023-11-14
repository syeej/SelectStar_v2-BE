package com.threestar.selectstar.dto.mypage;

import com.threestar.selectstar.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Getter
@Setter
@Builder
public class UpdateMyInfoRequest {
    //이력관리
    private int userId;
    private String name;
    private String aboutMe;
    private String profileContent;

    //개인정보
    private String password;
    private String email;
    private String nickname;
    private String location1;
    private String location2;
    private String interestLanguage;
    private String interestFramework;
    private String interestJob;

    private byte[] profilePhoto;


}
