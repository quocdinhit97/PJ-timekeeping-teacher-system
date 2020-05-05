package com.web.app.request;

import com.web.app.entity.UserInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
public class CreateTimeSheetRequest {

    @NotNull
    private Date teachDate;

    @NotNull
    private LocalTime fromTime;

    @NotNull
    private LocalTime toTime;

    @NotNull
    private String student;

    @NotNull
    private String contentTitle;

    @NotNull
    private String description;

    @NotNull
    private String note;

    @NotNull
    private UserInfo userInfo;
}
