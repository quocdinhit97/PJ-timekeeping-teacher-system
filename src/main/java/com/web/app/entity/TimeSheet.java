package com.web.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.web.app.common.TimeSheetStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class TimeSheet {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "TEACH_DATE")
    private Date teachDate;

    @Column(name = "FROM_TIME")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime fromTime;

    @Column(name = "TO_TIME")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime toTime;

    @Column(name = "TOTAL_TIME")
    @Max(3)
    @Min(1)
    private double totalTime;

    @Column(name = "STUDENT")
    private String student;

    @Column(name = "CONTENT_TITLE")
    private String contentTitle;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TimeSheetStatus status = TimeSheetStatus.APPROVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    public Long minutes() {
        return Duration.between(getFromTime(), getToTime()).toMinutes();
    }

    public double hours() {
        return minutes() / 60d;
    }
}
