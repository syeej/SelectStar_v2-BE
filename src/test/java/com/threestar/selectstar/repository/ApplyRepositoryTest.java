package com.threestar.selectstar.repository;

import com.threestar.selectstar.domain.entity.Apply;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
@DataJpaTest
class ApplyRepositoryTest {

    @Autowired
    ApplyRepository applyRepository;

    void 모든_목록_조회(){
        List<Apply> all = applyRepository.findAll();
        all.forEach(System.out::println);
    }
}