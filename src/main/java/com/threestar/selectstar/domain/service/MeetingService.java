package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.Meeting;
import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.meeting.request.AddUpdateMeetingRequest;
import com.threestar.selectstar.dto.meeting.request.FindMainPageRequest;
import com.threestar.selectstar.dto.meeting.response.FindMeetingOneResponse;
import com.threestar.selectstar.dto.meeting.response.FindMainPageResponse;
import com.threestar.selectstar.dto.mypage.GetMyApplyingListResponse;
import com.threestar.selectstar.dto.mypage.GetMyInfoResponse;
import com.threestar.selectstar.dto.mypage.GetMyMeetingListResponse;
import com.threestar.selectstar.exception.MeetingNotFoundException;
import com.threestar.selectstar.repository.ApplyRepository;
import com.threestar.selectstar.repository.CommentRepository;
import com.threestar.selectstar.repository.MeetingRepository;
import com.threestar.selectstar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
		return byDeletedIsOrderByCreationDateDesc.map(entity -> FindMainPageResponse.fromEntity(entity,
			commentRepository.countByMeeting_MeetingIdIs(entity.getMeetingId())));
	}
	public FindMeetingOneResponse findMeetingOne(int meetingId){
		return meetingRepository.findById(meetingId).
			map(meeting -> FindMeetingOneResponse.fromEntity(meeting, meeting.getUser().getNickname()))
			.orElse(null);
	}

	@Transactional
	public String addMeeting(AddUpdateMeetingRequest addUpdateMeetingRequest){
		try {
			meetingRepository.save(AddUpdateMeetingRequest.toEntity
				(addUpdateMeetingRequest,userRepository.findById
						(addUpdateMeetingRequest.getUserId())
					.orElseThrow(IllegalArgumentException::new)));
			return "success";
		} catch (Exception e){
			return e.getMessage();
		}
	}
	@Transactional
	public String updateMeeting(AddUpdateMeetingRequest addUpdateMeetingRequest){
		try {
			meetingRepository.save(AddUpdateMeetingRequest.toEntity(
				addUpdateMeetingRequest,
				userRepository.findById(addUpdateMeetingRequest.getUserId())
					.orElseThrow(IllegalArgumentException::new)));
			return "success";
		} catch (Exception e){
			return e.getMessage();
		}
	}
	@Transactional
	public String removeMeeting(int id){
		try {
			meetingRepository.findById(id)
				.orElseThrow(IllegalArgumentException::new)
				.setDeleted(1);
			return "success";
		} catch (Exception e){
			return e.getMessage();
		}
	}

	//내가 작성한 글 목록 조회[마이페이지]
	public List<GetMyMeetingListResponse> getMyMeetingList(int uId){
		List<Meeting> myMeetingList = meetingRepository.findByUser_UserIdIsAndDeletedIs(uId, 0);
		//log.info("meetinglist entity  >>"+myMeetingList);
		if(myMeetingList.isEmpty()){
			return null;
		}else {
			List<GetMyMeetingListResponse> dtoList = new ArrayList<>();
			for (Meeting meeting : myMeetingList) {
				dtoList.add(GetMyMeetingListResponse.fromEntity(meeting));
			}
			log.info("meetinglist dto  >>" + dtoList);
			return dtoList;
		}
	}

	//내가 작성한 글 목록 카테고리별/모집상태별 조회[마이페이지]
	public List<GetMyMeetingListResponse> getMyMeetingListByFilter(int uId, String strCategory, String strStatus){
		int category = 0, status=0;
		//카테고리 value(전체: all/프로젝트: project/스터디: study/기타: etc)
		if(strCategory != null){
			if(strCategory.equals("project")){
				category = 1;
			}else if(strCategory.equals("study")){
				category = 0;
			}else if(strCategory.equals("etc")){
				category = 2;
			}else if(strCategory.equals("all")){
				strCategory = null;
			}
		}
		//모집상태 value(전체: all/모집중: statusing/모집완료: statused)
		if(strStatus != null){
			if(strStatus.equals("statusing")){
				status = 0;
			}else if(strStatus.equals("statused")){
				status = 1;
			}else if(strStatus.equals("all")){
				strStatus = null;
			}
		}
		List<Meeting> entityList;
		if((strStatus != null && !strStatus.isEmpty()) && (strCategory != null && !strCategory.isEmpty())){
			entityList = meetingRepository.findByUser_UserIdIsAndDeletedIsAndCategoryIsAndStatusIs(uId, 0, category, status);
		}else if((strStatus == null) && (strCategory != null && !strCategory.isEmpty())){
			entityList = meetingRepository.findByUser_UserIdIsAndDeletedIsAndCategoryIs(uId, 0, category);
		}else if((strStatus != null && !strStatus.isEmpty()) && (strCategory == null)){
			entityList = meetingRepository.findByUser_UserIdIsAndDeletedIsAndStatusIs(uId, 0, status);
		}else{
			entityList = meetingRepository.findByUser_UserIdIsAndDeletedIs(uId, 0);
		}
		if(entityList.isEmpty()){
			return null;
		}else {
			List<GetMyMeetingListResponse> dtoList = new ArrayList<>();
			for (Meeting meeting :entityList) {
				dtoList.add(GetMyMeetingListResponse.fromEntity(meeting));
			}
			//log.info("meetinglist dto  >>" + dtoList);
			return dtoList;
		}
	}

	// 모임글 검색 - 제목만
	@Transactional(readOnly = true)
	public List<FindMainPageResponse> searchMeeting(String searchWord) {
		List<Meeting> searchMeeting = meetingRepository.findByTitleLikeAndDeletedIsOrderByCreationDateDesc(
			"%" + searchWord + "%", 0);

		return searchMeeting.stream()
			.map(meeting -> FindMainPageResponse.fromEntity(meeting, commentRepository.countByMeeting_MeetingIdIs(meeting.getMeetingId())))
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
			.map(meeting -> FindMainPageResponse.fromEntity(meeting, commentRepository.countByMeeting_MeetingIdIs(meeting.getMeetingId())))
			.collect(Collectors.toList());
	}
    //내가 신청한 글 목록 조회[마이페이지]
    public List<GetMyApplyingListResponse> getMyApplyingList(int uId){

        List<Meeting> myApplyingList = meetingRepository.getMyApplyingList(uId, 0);
        log.info("applyinglist entity  >>"+myApplyingList);
        if(myApplyingList.isEmpty()){
            return null;
        }else {
            List<GetMyApplyingListResponse> dtoList = new ArrayList<>();
            for (Meeting meeting : myApplyingList) {
                dtoList.add(GetMyApplyingListResponse.fromEntity(meeting));
            }
            log.info("meetinglist dto  >>" + dtoList);
            return dtoList;
        }
    }
    //내가 신청한 글 목록 카테고리별/모집상태별 조회[마이페이지]
    public List<GetMyApplyingListResponse> getMyAppyingListByFilter(int uId, String strCategory, String strStatus){
        int category = 0, status=0;
        //카테고리 value(전체: all/프로젝트: project/스터디: study/기타: etc)
        if(strCategory != null){
            if(strCategory.equals("project")){
                category = 1;
            }else if(strCategory.equals("study")){
                category = 0;
            }else if(strCategory.equals("etc")){
                category = 2;
            }else if(strCategory.equals("all")){
                strCategory = null;
            }
        }
        //모집상태 value(전체: all/모집중: statusing/모집완료: statused)
        if(strStatus != null){
            if(strStatus.equals("statusing")){
                status = 0;
            }else if(strStatus.equals("statused")){
                status = 1;
            }else if(strStatus.equals("all")){
                strStatus = null;
            }
        }
        List<Meeting> entityList;
        if((strStatus != null && !strStatus.isEmpty()) && (strCategory != null && !strCategory.isEmpty())){
            System.out.println("cate, stat 다 있음 >"+category+status);
            entityList = meetingRepository.getMyApplyingListByCateAndSts(uId, 0, category, status);
        }else if((strStatus == null) && (strCategory != null && !strCategory.isEmpty())){
            System.out.println("cate 있음 >"+category);
            entityList = meetingRepository.getMyApplyingListByCate(uId, 0, category);
        }else if((strStatus != null && !strStatus.isEmpty()) && (strCategory == null)){
            System.out.println("stat 있음 >"+status);
            entityList = meetingRepository.getMyApplyingListByStatus(uId, 0, status);
        }else{
            System.out.println("cate, stat 다 없음 >"+category+status);
            entityList = meetingRepository.getMyApplyingList(uId, 0);
        }
        if(entityList.isEmpty()){
            return null;
        }else {
            List<GetMyApplyingListResponse> dtoList = new ArrayList<>();
            for (Meeting meeting :entityList) {
                dtoList.add(GetMyApplyingListResponse.fromEntity(meeting));
            }
            //log.info("meetinglist dto  >>" + dtoList);
            return dtoList;
        }
    }
}
