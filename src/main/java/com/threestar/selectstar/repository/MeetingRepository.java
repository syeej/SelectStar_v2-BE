package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Integer> {
    // 삭제 안 된 전체 게시물 리스트 조회
    List<Meeting> findByDeletedIs(int isDelete);
    //삭제 안 된 전체 게시물 수 조회
    Integer countByDeleted(int isDelete);
    // 카테고리가 지정된 삭제 안 된 게시글
    List<Meeting> findByDeletedIsAndCategoryIs(int deleted, int category);

}
