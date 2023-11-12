package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    UserRepository userRepository;
    // 사이트 댓글 조회
    @Test
    @DisplayName("사이트 댓글 조회")
    void findByMeeting_MeetingIdIsAndDeletedIs(){
        Pageable pageable = PageRequest.of(0,5);
        List<Comment> all = commentRepository.findAll();
        for (Object ele:
             all) {
            System.out.println(ele);

        }
      Page<Comment> byMeetingMeetingIdIsAndDeletedIs = commentRepository.findByMeeting_MeetingIdIsAndDeletedIs(64, 0, pageable);
       for (Object ele :
               byMeetingMeetingIdIsAndDeletedIs) {
            System.out.println(ele);
 }
    }
    // 댓글 등록

    // 댓글 삭제

    // 댓글 수정...? 뷰에서 어떻게
}