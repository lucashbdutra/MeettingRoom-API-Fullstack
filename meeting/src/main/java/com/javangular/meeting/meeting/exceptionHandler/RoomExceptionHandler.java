package com.javangular.meeting.meeting.exceptionHandler;

import com.javangular.meeting.meeting.exceptions.ErrorMessage;
import com.javangular.meeting.meeting.exceptions.RoomNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class RoomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception e, WebRequest request){

        ErrorMessage error = new ErrorMessage(new Date(),e.getMessage(),request.getDescription(false) );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoomNotFound.class)
    public ResponseEntity<ErrorMessage> roomNotFoundException(Exception e, WebRequest request){

        ErrorMessage error = new ErrorMessage(new Date(),e.getMessage(),request.getDescription(false) );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
