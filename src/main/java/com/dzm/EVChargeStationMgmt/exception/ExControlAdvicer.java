package com.dzm.EVChargeStationMgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExControlAdvicer {
    @ExceptionHandler(value = NoRecordsFoundException.class)
    public ResponseEntity<Object> exception(NoRecordsFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }


}
