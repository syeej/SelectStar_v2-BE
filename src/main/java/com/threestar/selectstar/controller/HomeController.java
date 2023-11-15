package com.threestar.selectstar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threestar.selectstar.domain.service.MeetingService;
import com.threestar.selectstar.domain.service.UserService;
import com.threestar.selectstar.dto.meeting.response.FindMainPageResponse;
import com.threestar.selectstar.dto.user.response.GetUsersListResponse;
@CrossOrigin(originPatterns = {"http://localhost:5173","http://localhost:63342"})
@RestController
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private	MeetingService meetingService;

	// 검색 (제목)
	@GetMapping("/search")
	public ResponseEntity<List<Object>> searchResult(@RequestParam("q") String searchWord) {
		List<Object> searchResult = new ArrayList<>();

		// 모임 검색
		List<FindMainPageResponse> meetings = meetingService.searchMeeting(searchWord);
		searchResult.add(meetings);

		// 회원 검색
		List<GetUsersListResponse> users = userService.searchUser(searchWord);
		searchResult.add(users);

		return ResponseEntity.ok(searchResult);
	}

	// 검색 (모임글 필터링)
	@GetMapping("/search/filter")
	public ResponseEntity<List<FindMainPageResponse>> searchMeetingWithFilter(
		@RequestParam("q") String searchWord,
		@RequestParam(name = "category", required = false) List<Integer> category,
		@RequestParam(name = "languages", required = false) List<String> languages,
		@RequestParam(name = "frameworks", required = false) List<String> frameworks,
		@RequestParam(name = "jobs", required = false) List<String> jobs){

		List<FindMainPageResponse> searchResult = meetingService.searchMeetingWithFilter(searchWord, category, languages, frameworks, jobs);

		if (searchResult.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(searchResult);
	}

	// 인기글 조회
	@GetMapping("/rankMeeting")
	public ResponseEntity<Page<FindMainPageResponse>> getMeetingListRank(){
		return ResponseEntity.ok()
				.body(meetingService.getMeetingListRank());
	}
}
