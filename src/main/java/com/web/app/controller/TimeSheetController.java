package com.web.app.controller;

import com.web.app.entity.UserInfo;
import com.web.app.repository.TimeSheetRepository;
import com.web.app.repository.UserInfoRepository;
import com.web.app.request.CreateTimeSheetRequest;
import com.web.app.service.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TimeSheetController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    TimeSheetRepository timeSheetRepository;

    @Autowired
    TimeSheetService timeSheetService;


    // -------- user ------//
    @GetMapping("/teacher/myTimeSheet")
    public String myTimeSheet(Model model, Principal principal){
        UserInfo userInfo = userInfoRepository.findByUsername(principal.getName());
        List<TimeSheet> timeSheets = timeSheetRepository.findByUserInfo(userInfo);
        if(timeSheets == null){
            return "index";
        }
        model.addAttribute("userTimeSheet", timeSheets);
        return "views/my_time_sheet";
    }

    @GetMapping("/teacher/createTimeSheet")
    public String createTimeSheet(Model model){
        model.addAttribute("createTimeSheetRequest", new CreateTimeSheetRequest());
        return "views/create_time_sheet";
    }

    @PostMapping("/teacher/createLogTime")
    public String createTimeSheet(Model model, @Valid CreateTimeSheetRequest request, BindingResult result, Principal principal) throws ParseException {

        if(result.hasErrors()){
            return "views/create_time_sheet";
        }

        UserInfo userInfo = userInfoRepository.findByUsername(principal.getName());

        timeSheetService.createTimeSheet(request, userInfo);

        List<TimeSheet> timeSheets = timeSheetRepository.findByUserInfo(userInfo);
        if(timeSheets == null){
            return "index";
        }
//        model.addAttribute("userTimeSheet", timeSheets);
        return "redirect:/teacher/myTimeSheet";
    }

    //------ admin --------//
    @GetMapping("/admin/timeSheet")
    public String timeSheet(Model model){
        model.addAttribute("userList", userInfoRepository.findAll());
        model.addAttribute("timeSheetList", timeSheetRepository.findAll());
        return "views/user_time_sheet";
    }

    @GetMapping("/timeSheet/update/{timSheetId}")
    public RedirectView updateStatusTimeSheet(Model model, @PathVariable Long timSheetId){

        TimeSheet timeSheet = timeSheetRepository.findById(timSheetId).get();

        if (timeSheet.getStatus().equals(TimeSheetStatus.APPROVE)) {
            timeSheet.setStatus(TimeSheetStatus.REJECT);
        } else {
            timeSheet.setStatus(TimeSheetStatus.APPROVE);
        }

        timeSheetRepository.save(timeSheet);
        model.addAttribute("userList", userInfoRepository.findAll());
        model.addAttribute("timeSheetList", timeSheetRepository.findAll());
        return new RedirectView("/timeSheet");
    }


}
