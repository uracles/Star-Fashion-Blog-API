package com.merakool.star_fashion.exceptions;

public class UnauthorizedException extends RuntimeException{
    private String message;

    public UnauthorizedException() {
        super("Unauthorized ");
        this.message = "Unauthorized";

    }

    public UnauthorizedException(String msg) {
        super(msg);
        this.message = msg;
    }
}
