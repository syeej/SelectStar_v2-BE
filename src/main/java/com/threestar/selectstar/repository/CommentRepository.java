package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Comment;
import com.threestar.selectstar.dto.meeting.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    // 사이트 댓글 전체 조회
    Page<Comment> findByMeeting_MeetingIdIsAndDeletedIs(Integer meeting_meetingId, int deleted, Pageable pageable);
    // 댓글 등록

    // 댓글 삭제

    // 댓글 수정...? 뷰에서 어떻게
}
