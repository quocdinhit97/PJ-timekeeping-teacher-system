package com.web.app.entity;

import com.web.app.common.WebRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "USER_INFO")
public class UserInfo {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FULLNAME", nullable = false)
    private String fullName;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "IS_BLOCK")
    private Boolean isBlock = false;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private WebRole role = WebRole.USER;
}
