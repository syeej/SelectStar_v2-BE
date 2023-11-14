package com.threestar.selectstar.dto.user.request;

import java.sql.Date;

import com.threestar.selectstar.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
	private String name;
	private String password;
	private String email;
	private String nickname;
	private String location1;
	private Date joinDate;
	private String interestLanguage;
	private String interestFramework;
	private String interestJob;

	public User toEntity(){
		return User.builder()
			.name(name)
			.password(password)
			.email(email)
			.nickname(nickname)
			.location1(location1)
			.joinDate(joinDate)
			.interestLanguage(interestLanguage)
			.interestFramework(interestFramework)
			.interestJob(interestJob)
			.build();
	}
}
