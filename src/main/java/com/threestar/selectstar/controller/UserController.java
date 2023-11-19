package com.threestar.selectstar.controller;

import com.threestar.selectstar.dto.user.response.GetUserProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.threestar.selectstar.domain.service.UserService;
import com.threestar.selectstar.dto.user.request.AddUserRequest;
import com.threestar.selectstar.dto.user.request.GetUserRequest;

@CrossOrigin(originPatterns = {"*"})
@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 회원가입 페이지 이동
	@GetMapping("/signup")
	public String goSignupPage() {
		return "signup";
	}

	// 회원가입 처리
	@PostMapping("/users")
	public ResponseEntity<String> processSignup (@RequestBody AddUserRequest request) {
		try{
			Integer userId = userService.addUser(request);
			return ResponseEntity.ok("회원가입 성공 id: "+userId);
		} catch (IllegalStateException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// 로그인 페이지 이동
	@GetMapping("/login")
	public String goLoginPage(){
		return "login";
	}

	// 로그인 처리
	@PostMapping("/login")
	public String processLogin(GetUserRequest request){
		boolean success = userService.login(request);
		if (success) {
			return "redirect:/";
		} else {
			return "login";
		}
	}

	//다른 유저 프로필 조회
	@GetMapping("/profiles/info/{id}")
	@ResponseBody
	public ResponseEntity<?> getUserProfile(@PathVariable int id){
		GetUserProfileResponse res = userService.getUserProfile(id);
		log.info("res >>"+res);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

}
