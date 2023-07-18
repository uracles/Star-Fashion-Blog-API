package com.merakool.star_fashion.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class NotFoundException extends RuntimeException{
    private String message;

    public NotFoundException() {
        super("Not found");
        this.message = "Not found";

    }

    public NotFoundException(String msg){
        super(msg);
        this.message=msg;
    }
//private final String message;
//
////    private HttpStatus status;
//
//    private final LocalDateTime time = LocalDateTime.now();
//
//    public NotFoundException(String message) {
//        this.message = message;
//    }
//
//    public NotFoundException(String message, HttpStatus status) {
//        this.message = message;
////        this.status = status;
//    }
}
