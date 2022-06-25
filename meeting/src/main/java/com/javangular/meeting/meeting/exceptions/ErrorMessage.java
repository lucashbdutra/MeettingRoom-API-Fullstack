package com.javangular.meeting.meeting.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorMessage {

    private Date timestamp;
    private String message;
    private String details;



}
