package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.mypage.response.GetMyInfoResponse;
import com.threestar.selectstar.dto.mypage.request.UpdateMyInfoRequest;
import com.threestar.selectstar.dto.mypage.UserImgFileDTO;
import com.threestar.selectstar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MypageService {

    private final UserRepository userRepository;

    //마이페이지 이력관리 조회 요청(UserService 이동 예정)
    public GetMyInfoResponse getMyProfileInfo(int id){
        //Optional : NPE(NullPointerException) 방지 => orElseTrow 사용 개선?
        Optional<User> userO = userRepository.findById(id);
        if(userO.isEmpty()){
            return null;
        }else {
            User userE = userO.get();
            //기본 이미지
            //String encodeImg = "/image/global/userdefaultimg.png";
            String encodeImg = "";
            byte[] imgByte = userE.getProfilePhoto();
            //유저 이미지 있으면 변환
            if(imgByte != null){
                encodeImg = "data:image/png;base64,"+Base64.getEncoder().encodeToString(imgByte);
            }
            System.out.println("encodeImg >>"+encodeImg);
            return GetMyInfoResponse.builder()
                    .userId(id)
                    .nickname(userE.getNickname())
                    .email(userE.getEmail())
                    .profilePhoto(encodeImg)
                    .aboutMe(userE.getAboutMe())
                    .profileContent(userE.getProfileContent())
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
            //기본 이미지
            String encodeImg = "/assets/image/global/userdefaultimg.png";
            byte[] imgByte = userE.getProfilePhoto();
            //유저 이미지 있으면 변환
            if(imgByte != null){
                encodeImg = "data:image/png;base64,"+Base64.getEncoder().encodeToString(imgByte);
            }
            System.out.println("encodeImg >>"+encodeImg);
            return GetMyInfoResponse.builder()
                    .userId(id)
                    .name(userE.getName())
                    .password(userE.getPassword())
                    .email(userE.getEmail())
                    .nickname(userE.getNickname())
                    .profilePhoto(encodeImg)
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
    //프로필 이미지 수정
    @Transactional
    public String updateMyProfileImg(int uId, UserImgFileDTO fileDTO){
        Optional<User> userO = userRepository.findById(uId);
        if(userO.isEmpty()){
            return "찾는 사용자가 없습니다.";
        }else {
            User oldUserE = userO.get();
            byte[] byteImg = null;
            try {
                byteImg = fileDTO.getProfilePhoto().getBytes();
                oldUserE.setProfilePhoto(byteImg);
                return "success";
            }catch (Exception e){
                log.info("update profile img error"+e.getMessage());
                return e.getMessage();
            }
        }
    }
}
