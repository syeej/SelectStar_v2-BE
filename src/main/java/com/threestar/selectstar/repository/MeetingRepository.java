package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Integer> {
    // 삭제 안 된 전체 게시물 리스트 조회
    Page<Meeting> findByDeletedIsOrderByCreationDateDesc(int isDelete, Pageable pageable);
    //삭제 안 된 전체 게시물 수 조회
    Integer countByDeleted(int isDelete);
    // 카테고리가 지정된 삭제 안 된 게시글
    List<Meeting> findByDeletedIsAndCategoryIsOrderByCreationDateDesc(int deleted, int category);
    // 카테고리가 지정된 삭제 안 된 게시글 수
    Integer countByDeletedAndCategoryIsOrderByViewsDesc(int deleted, int category);
    // 메인 - 인기글 조회 (RANK) : 최근 일주일간 올라온 글 중에서 조회수 높은 것 10개
    Page<Meeting> findByDeletedIsAndCreationDateIsGreaterThanEqual(int deleted, Date creationDate, Pageable pageable);
    // 모임글 검색
    List<Meeting> findByTitleLikeAndDeletedIsOrderByCreationDateDesc(String like, int deleted);
    // 내가 작성한 글 목록
    List<Meeting> findByUser_UserIdIsAndDeletedIs(Integer user_userId, int deleted);
    // 내가 작성한 글목록 조회(카테고리별)
    List<Meeting> findByUser_UserIdIsAndDeletedIsAndCategoryIs(Integer user_userId, int deleted, int category);
    // 내가 작성한 글목록 조회(모집상태별)
    List<Meeting> findByUser_UserIdIsAndDeletedIsAndStatusIs(Integer user_userId, int deleted, int status);
    // 내가 작성한 글목록 조회(카테고리별, 모집상태별)
    List<Meeting> findByUser_UserIdIsAndDeletedIsAndCategoryIsAndStatusIs(Integer user_userId, int deleted, int category, int status);

}
