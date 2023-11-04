package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
