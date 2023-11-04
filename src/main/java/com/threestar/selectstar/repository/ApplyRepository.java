package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Apply;
import com.threestar.selectstar.domain.entity.ApplyID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, ApplyID> {

}
