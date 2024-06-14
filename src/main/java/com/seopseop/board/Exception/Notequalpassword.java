package com.seopseop.board.Exception;

public class Notequalpassword extends RuntimeException{
    public Notequalpassword () {
        super("Please check your typing. Not equal password");
    }
}
