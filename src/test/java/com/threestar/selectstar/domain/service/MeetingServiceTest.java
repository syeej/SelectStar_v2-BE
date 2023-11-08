package com.threestar.selectstar.domain.service;

import com.threestar.selectstar.dto.MeetingDTO;
import com.threestar.selectstar.repository.MeetingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// SpringJUnit4ClassRunner.class: JUnit의 확장 기능을 사용할 수 있도록 스프링에서 제공함
// 목적: 테스트코드를 실행 시 스프링 빈 컨테이너가 내부적으로 생성되도록 함
class MeetingServiceTest {


    @Autowired
    private MeetingService meetingService;

    @Test
    void getPageMeetingDTO() {
        Pair<Integer, List<MeetingDTO>> integerListPair = meetingService.GetPageMeetingDTO(0, 10);

    }

}