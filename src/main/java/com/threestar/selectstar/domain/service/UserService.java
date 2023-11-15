package com.threestar.selectstar.domain.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.threestar.selectstar.dto.user.response.GetUserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.user.request.AddUserRequest;
import com.threestar.selectstar.dto.user.request.GetUserRequest;
import com.threestar.selectstar.dto.user.response.GetUsersListResponse;
import com.threestar.selectstar.repository.UserRepository;

@Service
public class UserService {

	final UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 회원 가입 ( 구현 예정 - 중복 확인, 비밀번호 )
	@Transactional
	public Integer addUser(AddUserRequest request){
		validateDuplicateUser(request);  // 중복 확인
		User savedUser = userRepository.save(request.toEntity());
		return savedUser.getUserId();
	}

	private void validateDuplicateUser(AddUserRequest request) {
		userRepository.findByName(request.getName())
			.ifPresent(u ->{
				throw new IllegalStateException("이미 존재하는 아이디입니다.");
			});
	}

	// 로그인
	public boolean login(GetUserRequest request) {
		return userRepository.findByNameAndPassword(request.getName(), request.getPassword()).isPresent();
	}

	// 회원 검색
	@Transactional(readOnly = true)
	public List<GetUsersListResponse> searchUser(String searchWord) {
		List<User> searchUser = userRepository.findByNicknameLike("%"+searchWord+"%");
		return searchUser.stream()
			.map(GetUsersListResponse::fromEntity)
			.collect(Collectors.toList());
	}

	//다른 유저 프로필 조회
	public GetUserProfileResponse getUserProfile(int id){
		Optional<User> userO = userRepository.findById(id);
		if(userO.isEmpty()){
			return null;
		}else{
			User userE = userO.get();
			//기본 이미지
			String encodeImg = "/image/global/userdefaultimg.png";
			byte[] imgByte = userE.getProfilePhoto();
			//유저 이미지 있으면 변환
			if(imgByte != null){
				encodeImg = "data:image/png;base64,"+ Base64.getEncoder().encodeToString(imgByte);
			}
			return GetUserProfileResponse.builder()
					.userId(id)
					.name(userE.getName())
					.nickname(userE.getNickname())
					.email(userE.getEmail())
					.profilePhoto(encodeImg)
					.aboutMe(userE.getAboutMe())
					.profileContent(userE.getProfileContent())
					.build();
		}
	}

}
