package com.web.app.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UpdateUserRequest {

    private String password;

    @NotEmpty(message = "Please enter a valid full name")
    private String fullName;

    @NotEmpty(message = "Please enter a valid email")
    @Email(message = "please enter your email")
    private String email;
}
