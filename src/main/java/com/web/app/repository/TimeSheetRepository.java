package com.web.app.repository;

import com.web.app.entity.TimeSheet;
import com.web.app.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long>{
    List<TimeSheet> findByUserInfo(UserInfo userInfo);
    Optional<TimeSheet> findById(Long id);
}
