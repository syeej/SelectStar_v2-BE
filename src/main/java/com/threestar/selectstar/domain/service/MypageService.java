package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.mypage.GetMyInfoResponse;
import com.threestar.selectstar.dto.mypage.UpdateMyInfoRequest;
import com.threestar.selectstar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MypageService {

    private final UserRepository userRepository;

    //마이페이지 이력관리 조회 요청(UserService 이동 예정)
    public GetMyInfoResponse getMyProfileInfo(int id){
        //Optional : NPE(NullPointerException) 방지
        Optional<User> userO = userRepository.findById(id);
        if(userO.isEmpty()){
            return null;
        }else {
            User userE = userO.get();
            return GetMyInfoResponse.builder()
                    .userId(id)
                    .name(userE.getName())
                    .aboutMe(userE.getAboutMe())
                    .profileContent(userE.getProfileContent())
                    .profilePhoto(userE.getProfilePhoto())
                    .build();
            //return new GetMyInfoResponse(userE);
        }
    }

    //마이페이지 이력관리 수정 요청(UserService 이동 예정)
    @Transactional
    public String updateMyProfileInfo(int uId, UpdateMyInfoRequest reqDTO){
        Optional<User> userO = userRepository.findById(uId);
        if(userO.isEmpty()){
            return "찾는 사용자가 없습니다.";
        }else {
            User oldUserEntity = userO.get();
            oldUserEntity.setAboutMe(reqDTO.getAboutMe());
            oldUserEntity.setProfileContent(reqDTO.getProfileContent());
            try {
                userRepository.save(oldUserEntity);
                return "success";
            } catch (Exception e) {
                log.info("update myinfo exception", e.getMessage());
                return e.getMessage();
            }
        }
    }

    //마이페이지 개인정보 조회 요청(UserService 이동 예정)
    public GetMyInfoResponse getMyInfo(int id){
        //Optional : NPE(NullPointerException) 방지
        Optional<User> userO = userRepository.findById(id);
        if(userO.isEmpty()){
            return null;
        }else {
            User userE = userO.get();
            return GetMyInfoResponse.builder()
                    .userId(id)
                    .name(userE.getName())
                    .password(userE.getPassword())
                    .email(userE.getEmail())
                    .nickname(userE.getNickname())
                    .location1(userE.getLocation1())
                    .location2(userE.getLocation2())
                    .interestLanguage(userE.getInterestLanguage())
                    .interestFramework(userE.getInterestFramework())
                    .interestJob(userE.getInterestJob())
                    .build();
            //return new GetMyInfoResponse(userE);
        }
    }

    //마이페이지 개인정보 수정 요청(UserService 이동 예정)
    @Transactional
    public String updateMyInfo(int uId, UpdateMyInfoRequest reqDTO){
        Optional<User> userO = userRepository.findById(uId);
        if(userO.isEmpty()){
            return "찾는 사용자가 없습니다.";
        }else {
            User oldUserEntity = userO.get();
            oldUserEntity.setPassword(reqDTO.getPassword());
            oldUserEntity.setEmail(reqDTO.getEmail());
            oldUserEntity.setNickname(reqDTO.getNickname());
            oldUserEntity.setLocation1(reqDTO.getLocation1());
            oldUserEntity.setLocation2(reqDTO.getLocation2());
            oldUserEntity.setInterestLanguage(reqDTO.getInterestLanguage());
            oldUserEntity.setInterestFramework(reqDTO.getInterestFramework());
            oldUserEntity.setInterestJob(reqDTO.getInterestJob());
            try {
                userRepository.save(oldUserEntity);
                return "success";
            } catch (Exception e) {
                log.info("update myinfo exception", e.getMessage());
                return e.getMessage();
            }
        }
    }

}
