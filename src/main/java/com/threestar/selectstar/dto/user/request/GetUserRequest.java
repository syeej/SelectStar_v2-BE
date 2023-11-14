package com.threestar.selectstar.dto.user.request;

import com.threestar.selectstar.domain.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserRequest {
	private String name;
	private String password;

	public User toEntity(){
		return User.builder()
			.name(name)
			.password(password)
			.build();
	}

}
