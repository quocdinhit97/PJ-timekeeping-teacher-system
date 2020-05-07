package com.web.app.controller;

import com.web.app.repository.TimeSheetRepository;
import com.web.app.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimeSheetController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    TimeSheetRepository timeSheetRepository;

    @GetMapping("/timeSheet")
    public String timeSheet(Model model){
        model.addAttribute("userList", userInfoRepository.findAll());
        model.addAttribute("timeSheetList", timeSheetRepository.findAll());
        return "views/user_time_sheet";
    }

}
