package com.threestar.selectstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.threestar.selectstar.domain.service.UserService;
import com.threestar.selectstar.dto.AddUserRequest;
import com.threestar.selectstar.dto.GetUserRequest;

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


}
