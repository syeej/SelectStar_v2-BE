package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.domain.entity.User;
import com.threestar.selectstar.dto.GetMyInfoResponse;
import com.threestar.selectstar.dto.UpdateMyInfoRequest;
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

    //마이페이지 이력관리 조회 요청
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
                    .build();
            //return new GetMyInfoResponse(userE);
        }
    }

    //마이페이지 이력관리 수정 요청
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
}
