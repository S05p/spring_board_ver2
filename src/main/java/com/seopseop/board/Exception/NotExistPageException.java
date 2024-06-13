package com.seopseop.board.Exception;

public class NotExistPageException extends RuntimeException{

    public NotExistPageException() {
        super("Not exist list page. Please check your list page address");
    }
}
