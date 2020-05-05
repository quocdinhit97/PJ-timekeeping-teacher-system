package com.web.app.repository;

import com.web.app.entity.TimeSheet;
import com.web.app.entity.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long>{

    TimeSheet findByUserInfo(Long userId);
}
