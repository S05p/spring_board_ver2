package com.seopseop.board.Exception;

public class NotAuthorOfPost extends RuntimeException {

    public NotAuthorOfPost() {
        super("Your are not the author of the post.");
    }
}
