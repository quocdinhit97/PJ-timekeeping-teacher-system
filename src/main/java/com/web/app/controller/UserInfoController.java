package com.web.app.controller;

import com.web.app.entity.UserInfo;
import com.web.app.repository.UserInfoRepository;
import com.web.app.request.CreateUserRequest;
import com.web.app.request.UpdateUserRequest;
import com.web.app.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoRepository userInfoRepository;


    //-------------------router--------------//
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("createUserRequest", new CreateUserRequest());
        return "views/register_form";
    }

    @GetMapping("/timeSheet")
    public String timeSheet(){
        return "views/user_time_sheet";
    }

    @GetMapping("/userManagement")
    public String userManagement(Model model){
        model.addAttribute("users", userInfoService.findAllUser());
        return "views/user_management";
    }

    @GetMapping("updateUser/{userId}")
    public String showEditForm(Model model, @PathVariable Long userId){
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id: "+ userId));
        userInfo.setPassword(null);
        model.addAttribute("user", userInfo);
        return "views/edit_form";
    }

    //---------------function----------------//
    @PostMapping("/register")
    public String registerUser(@Valid CreateUserRequest request, BindingResult result, Model model){
        if(result.hasErrors()){
            return "views/register_form";
        }

        if(userInfoService.isUserPresent(request.getUsername())){
            model.addAttribute("exist", true);
            return "views/register_form";
        }

        userInfoService.createUser(request);
        model.addAttribute("success", true);
        model.addAttribute("users", userInfoService.findAllUser());
        return "views/user_management";

    }

    @GetMapping("/lockUnlock/{userId}")
    public RedirectView lockUnlock(@PathVariable Long userId, Model model){
        UserInfo userInfo = userInfoService.findUserById(userId);
        userInfo.setIsBlock(!userInfo.getIsBlock());
        userInfoRepository.save(userInfo);
        model.addAttribute("users", userInfoService.findAllUser());
        return new RedirectView("/userManagement");
    }

    @PostMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable Long userId, @Valid UpdateUserRequest request, BindingResult result, Model model){

        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id: "+ userId));

        if(result.hasErrors()){
            userInfo.setPassword(null);
            model.addAttribute("user", userInfo);
            return "views/edit_form";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userInfo.setEmail(request.getEmail());
        userInfo.setFullName(request.getFullName());
        if(request.getPassword() != null){
            userInfo.setPassword(encoder.encode(request.getPassword()));
        }


        userInfoRepository.save(userInfo);
        model.addAttribute("users", userInfoService.findAllUser());
        model.addAttribute("success", true);
        return "views/user_management";
    }
}
