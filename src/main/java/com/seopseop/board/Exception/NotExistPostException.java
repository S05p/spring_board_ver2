package com.seopseop.board.Exception;

public class NotExistPostException extends RuntimeException{
    public NotExistPostException() {
        super("The post is not exist. Please check the URL address");
    }
}
