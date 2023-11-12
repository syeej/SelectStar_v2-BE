package com.threestar.selectstar.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.domain.service.UserService;
import com.threestar.selectstar.dto.GetUsersListResponse;

@RestController
public class HomeController {

	@Autowired
	private UserService userService;

	// 회원 검색
	@GetMapping("/search")
	public ResponseEntity<List<GetUsersListResponse>> searchUser(@RequestParam("searchWord") String searchword) {
		List<GetUsersListResponse> responseList = userService.searchUser(searchword);

		return ResponseEntity.ok(responseList);
	}
}
