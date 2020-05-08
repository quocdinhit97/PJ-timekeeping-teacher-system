package com.web.app.service;

import com.web.app.entity.TimeSheet;
import com.web.app.repository.TimeSheetRepository;
import com.web.app.request.CreateTimeSheetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSheetService {

    @Autowired
    TimeSheetRepository timeSheetRepository;

    public void createTimeSheet(CreateTimeSheetRequest createTimeSheetRequest){

        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setContentTitle(createTimeSheetRequest.getContentTitle());
        timeSheet.setDescription(createTimeSheetRequest.getDescription());
        timeSheet.setFromTime(createTimeSheetRequest.getFromTime());
        timeSheet.setToTime(createTimeSheetRequest.getToTime());
        timeSheet.setNote(createTimeSheetRequest.getNote());
        timeSheet.setStudent(createTimeSheetRequest.getStudent());
        timeSheet.setTeachDate(createTimeSheetRequest.getTeachDate());
        double totalTime = timeSheet.hours();
        timeSheet.setTotalTime(totalTime);

        timeSheetRepository.save(timeSheet);

    }
}
