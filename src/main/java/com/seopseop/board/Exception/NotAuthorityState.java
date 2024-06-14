package com.seopseop.board.Exception;

public class NotAuthorityState extends RuntimeException{

    public NotAuthorityState () {
        super("Please check your session.");
    }
}
