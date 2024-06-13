package com.seopseop.board.Exception;

public class NotAuthoritytoCreatePostException extends RuntimeException{

    public NotAuthoritytoCreatePostException() {
        super("Your have not authority to write post. Please Login");
    }
}
