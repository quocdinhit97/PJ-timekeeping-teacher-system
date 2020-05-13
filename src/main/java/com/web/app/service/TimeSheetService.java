package com.web.app.service;

import com.web.app.entity.TimeSheet;
import com.web.app.entity.UserInfo;
import com.web.app.repository.TimeSheetRepository;
import com.web.app.request.CreateTimeSheetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeSheetService {

    @Autowired
    TimeSheetRepository timeSheetRepository;

    public void createTimeSheet(CreateTimeSheetRequest createTimeSheetRequest, UserInfo userInfo) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setContentTitle(createTimeSheetRequest.getContentTitle());
        timeSheet.setDescription(createTimeSheetRequest.getDescription());
        timeSheet.setFromTime(
                formatterTime.parse(createTimeSheetRequest.getFromTime())
        );
        timeSheet.setToTime( formatterTime.parse(createTimeSheetRequest.getToTime()));
        timeSheet.setNote(createTimeSheetRequest.getNote());
        timeSheet.setStudent(createTimeSheetRequest.getStudent());
        timeSheet.setTeachDate(formatter.parse(createTimeSheetRequest.getTeachDate()));
        timeSheet.setTotalTime(2);
        timeSheet.setUserInfo(userInfo);

        Date fromTime = formatterTime.parse(createTimeSheetRequest.getFromTime());
        Date toTime = formatterTime.parse(createTimeSheetRequest.getToTime());
        double totalTime = (toTime.getTime() - fromTime.getTime()) / (double)(60 * 60 * 1000) % 24;

        if (totalTime > 0){
            timeSheet.setTotalTime(totalTime);
        }

        timeSheetRepository.save(timeSheet);
    }
}
