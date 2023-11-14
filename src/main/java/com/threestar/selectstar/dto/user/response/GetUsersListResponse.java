package com.threestar.selectstar.dto.user.response;

import com.threestar.selectstar.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUsersListResponse {
	private int userId;
	private String nickname;
	private String aboutMe;

	public static GetUsersListResponse fromEntity(User user){
		return GetUsersListResponse.builder()
			.userId(user.getUserId())
			.nickname(user.getNickname())
			.aboutMe(user.getAboutMe())
			.build();
	}
}
