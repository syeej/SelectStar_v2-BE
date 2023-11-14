package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.Meeting;
import com.threestar.selectstar.dto.meeting.request.AddUpdateMeetingRequest;
import com.threestar.selectstar.dto.meeting.request.FindMainPageRequest;
import com.threestar.selectstar.dto.meeting.response.FindMeetingOneResponse;
import com.threestar.selectstar.dto.meeting.response.FindMainPageResponse;
import com.threestar.selectstar.repository.ApplyRepository;
import com.threestar.selectstar.repository.CommentRepository;
import com.threestar.selectstar.repository.MeetingRepository;
import com.threestar.selectstar.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetingService {

	final MeetingRepository meetingRepository;
	final UserRepository userRepository;
	final CommentRepository commentRepository;
	final ApplyRepository applyRepository;

	public MeetingService(MeetingRepository meetingRepository, UserRepository userRepository,
		CommentRepository commentRepository, ApplyRepository applyRepository) {
		this.meetingRepository = meetingRepository;
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
		this.applyRepository = applyRepository;
	}

	// 미팅 페이지를 조회한다.
	public Page<FindMainPageResponse> findMainPage(FindMainPageRequest findMainPageRequest) {
		// 총 페이지 랑 페이지 리스트 반환
		Pageable pageable;
		// 페이징 처리
		if (findMainPageRequest.getOrder() != null) {
			pageable = switch (findMainPageRequest.getOrder()) {
				case "desc" -> PageRequest.of(findMainPageRequest.getPage(),
					findMainPageRequest.getSize(),
					Sort.by(Sort.Direction.DESC,
						findMainPageRequest.getCriteria()));
				case "asc" -> PageRequest.of(findMainPageRequest.getPage(),
					findMainPageRequest.getSize(),
					Sort.by(Sort.Direction.ASC,
						findMainPageRequest.getCriteria()));
				default -> PageRequest.of(findMainPageRequest.getPage(),
					findMainPageRequest.getSize());
			};
		} else {
			pageable = PageRequest.of(findMainPageRequest.getPage(),
				findMainPageRequest.getSize());
		}
		Page<Meeting> byDeletedIsOrderByCreationDateDesc;
		if (findMainPageRequest.getCategory() == null)
			byDeletedIsOrderByCreationDateDesc = meetingRepository.findByDeletedIs(0,
				pageable);
		else
			byDeletedIsOrderByCreationDateDesc = meetingRepository.findByDeletedIsAndCategoryIs(0,
				findMainPageRequest.getCategory(),
				pageable);
		return byDeletedIsOrderByCreationDateDesc.map(FindMainPageResponse::fromEntity);
	}

	public FindMeetingOneResponse findMeetingOne(int meetingId) {
		return meetingRepository.findById(meetingId).
			map(meeting -> FindMeetingOneResponse.fromEntity(meeting, meeting.getUser().getNickname()))
			.orElse(null);
	}

	@Transactional
	public String addMeeting(AddUpdateMeetingRequest addUpdateMeetingRequest) {
		try {
			meetingRepository.save(AddUpdateMeetingRequest.toEntity
				(addUpdateMeetingRequest, userRepository.findById
						(addUpdateMeetingRequest.getUserId())
					.orElseThrow(IllegalArgumentException::new)));
			return "success";
		} catch (Exception e) {
			System.out.println(e);
			return e.getMessage();
		}
	}

	@Transactional
	public String updateMeeting(AddUpdateMeetingRequest addUpdateMeetingRequest) {
		try {
			meetingRepository.save(AddUpdateMeetingRequest.toEntity(
				addUpdateMeetingRequest,
				userRepository.findById(addUpdateMeetingRequest.getUserId())
					.orElseThrow(IllegalArgumentException::new)));
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Transactional
	public String removeMeeting(int id) {
		try {
			meetingRepository.findById(id)
				.orElseThrow(IllegalArgumentException::new)
				.setDeleted(1);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// 모임글 검색 - 제목만
	@Transactional(readOnly = true)
	public List<FindMainPageResponse> searchMeeting(String searchWord) {
		List<Meeting> searchMeeting = meetingRepository.findByTitleLikeAndDeletedIsOrderByCreationDateDesc(
			"%" + searchWord + "%", 0);
		return searchMeeting.stream()
			.map(FindMainPageResponse::fromEntity)
			.collect(Collectors.toList());
	}

	// 모임글 검색 - 필터링
	@Transactional(readOnly = true)
	public List<FindMainPageResponse> searchMeetingWithFilter(
		String searchWord, List<Integer> category, List<String> languages,
		List<String> frameworks, List<String> jobs) {

		List<Meeting> searchMeeting = meetingRepository.findBySearchFilter(
					searchWord, 0, category, languages, frameworks, jobs);
		return searchMeeting.stream()
			.map(FindMainPageResponse::fromEntity)
			.collect(Collectors.toList());
	}
}

