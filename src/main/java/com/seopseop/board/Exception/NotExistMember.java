package com.seopseop.board.Exception;


public class NotExistMember extends RuntimeException{
    public NotExistMember () {
        super("Not exist Member or resign Member");
    }
}
