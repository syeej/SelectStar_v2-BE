package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer> {
}