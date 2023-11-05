package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
