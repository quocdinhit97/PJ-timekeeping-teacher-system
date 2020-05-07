package com.web.app.controller;

import com.web.app.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm(Model model)
    {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

}
