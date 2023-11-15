package com.threestar.selectstar.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class GetUserProfileResponse {
    //다른 유저의 이력 확인
    private int userId;
    private String name;
    private String nickname;
    private String email;
    private String profilePhoto;
    private String aboutMe;
    private String profileContent;

}
