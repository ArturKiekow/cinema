package com.arturFerreira.cinema.exceptions.handler;

import com.arturFerreira.cinema.exceptions.CinemaException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CinemaException.class)
    public ProblemDetail CinemaExceptionHandler(CinemaException ex){
        return ex.toProblemDetail();
    }

}
