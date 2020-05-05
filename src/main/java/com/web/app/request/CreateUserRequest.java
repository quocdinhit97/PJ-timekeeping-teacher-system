package com.web.app.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserRequest {
    @NotEmpty(message = "Please enter a valid username")
    private String userName;
    @NotEmpty(message = "Please enter a valid password")
    private String password;
    @NotEmpty(message = "Please enter a valid full name")
    private String fullName;
    @NotEmpty(message = "Please enter a valid email")
    private String email;
}
