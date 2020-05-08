package com.web.app.controller;

import com.web.app.entity.UserInfo;
import com.web.app.repository.TimeSheetRepository;
import com.web.app.repository.UserInfoRepository;
import com.web.app.request.CreateTimeSheetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class TimeSheetController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    TimeSheetRepository timeSheetRepository;

    // -------- user ------//
    @GetMapping("myTimeSheet")
    public String myTimeSheet(Model model, Principal principal){
        UserInfo userInfo = userInfoRepository.findByUsername(principal.getName());
        model.addAttribute("userTimeSheet", timeSheetRepository.findByUserInfo(userInfo));
        return "views/my_time_sheet";
    }

    @GetMapping("/createTimeSheet")
    public String createTimeSheet(Model model){
        model.addAttribute("request", new CreateTimeSheetRequest());
        return "views/create_time_sheet";
    }


    //------ admin --------//
    @GetMapping("/timeSheet")
    public String timeSheet(Model model){
        model.addAttribute("userList", userInfoRepository.findAll());
        model.addAttribute("timeSheetList", timeSheetRepository.findAll());
        return "views/user_time_sheet";
    }

}
