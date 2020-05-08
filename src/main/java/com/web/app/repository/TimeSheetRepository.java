package com.web.app.repository;

import com.web.app.entity.TimeSheet;
import com.web.app.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long>{
    List<TimeSheet> findByUserInfo(UserInfo userInfo);

}
