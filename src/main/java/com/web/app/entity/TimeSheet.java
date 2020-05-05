package com.web.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
public class TimeSheet {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;

    @Column(name = "TEACH_DATE")
    private Date teachDate;

    @Column(name = "FROM_TIME")
    private Time fromTime;

    @Column(name = "TO_TIME")
    private Time toTime;

    @Column(name = "STUDENT")
    private String student;

    @Column(name = "CONTENT_TITLE")
    private String contentTitle;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NOTE")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;
}
