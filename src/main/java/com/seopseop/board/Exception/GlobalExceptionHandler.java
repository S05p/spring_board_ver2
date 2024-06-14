package com.seopseop.board.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotExistPostException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotExistPostException(NotExistPostException ex,
                                              Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/notExist";
    }

    @ExceptionHandler(NotExistPageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotExistPageException(NotExistPageException ex,
                                              Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/notExist";
    }

    @ExceptionHandler(NotAuthoritytoCreatePostException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleNotAuthoritytoCreatePostException(NotAuthoritytoCreatePostException ex,
                                                          Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/notExist";
    }

    @ExceptionHandler(NotAuthorOfPost.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleNotAuthorOfPost (NotAuthorOfPost ex,
                                         Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/notExist";
    }

    @ExceptionHandler(NotAuthorityState.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleNotAuthorityState (NotAuthorityState ex,
                                           Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/notExist";
    }

    @ExceptionHandler(Notequalpassword.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleNotequalpassword (Notequalpassword ex,
                                          Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "user/passwordchange.html";
    }

    @ExceptionHandler(NotExistMember.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotExistMember(NotExistMember ex,
                                       Model model) {
        model.addAttribute("errorMessage",ex.getMessage());
        return "user/notExist";
    }
}
