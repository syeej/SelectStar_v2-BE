package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Apply;
import com.threestar.selectstar.domain.entity.ApplyID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, ApplyID> {
    List<Apply> findByApplyID_Meeting_MeetingIdIs(Integer meeting_meetingId);

    List<Apply> findByApplyID_User_UserIdIs(Integer user_userId);

    boolean existsByApplyID_User_UserIdIsAndApplyID_Meeting_MeetingIdIs(Integer applyID_user_userId, Integer applyID_meeting_meetingId);


}
