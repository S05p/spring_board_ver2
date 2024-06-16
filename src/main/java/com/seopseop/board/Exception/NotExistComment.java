package com.seopseop.board.Exception;

public class NotExistComment extends RuntimeException{
    public NotExistComment () {
        super("Not exist parent comment");
    }
}
