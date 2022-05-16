package com.dzm.EVChargeStationMgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK,reason = "The Company/Station not found in the system")
public class NoRecordsFoundException extends RuntimeException {

    public NoRecordsFoundException(String message){
        super(message);
    }
    public NoRecordsFoundException(){
        super();
    }
 }
