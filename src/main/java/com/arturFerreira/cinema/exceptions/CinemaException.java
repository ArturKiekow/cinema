package com.arturFerreira.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CinemaException extends RuntimeException {

    public CinemaException() {
        super("Um erro inesperado aconteceu");
    }

    public CinemaException(String message) {
        super(message);
    }

    public ProblemDetail toProblemDetail(){
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal server error");
        pd.setDetail(this.getMessage());
        return pd;
    }

}
