package com.web.app.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

@Data
public class CreateTimeSheetRequest {

    private String teachDate;

    @NotEmpty(message = "Please provide from time")
    private String fromTime;

    @NotEmpty(message = "Please provide to time")
    private String toTime;

    @NotNull(message = "Please provide student")
    private String student;

    @NotEmpty(message = "Please provide title")
    private String contentTitle;

    @NotEmpty(message = "Please provide description")
    private String description;

    private String note;

}
