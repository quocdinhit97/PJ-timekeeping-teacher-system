package com.web.app.controller;

import com.web.app.request.CreateUserRequest;
import com.web.app.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("createUserRequest", new CreateUserRequest());
        return "views/registerForm";
    }

    @PostMapping("/register")
    public String registerUser(@Valid CreateUserRequest request, BindingResult result, Model model){
        if(result.hasErrors()){
            return "views/registerForm";
        }

        if(userInfoService.isUserPresent(request.getUsername())){
            model.addAttribute("exist", true);
            return "views/registerForm";
        }

        userInfoService.createUser(request);
        return "views/success";

    }
}
