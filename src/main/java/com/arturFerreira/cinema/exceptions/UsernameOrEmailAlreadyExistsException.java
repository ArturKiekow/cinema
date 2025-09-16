package com.arturFerreira.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UsernameOrEmailAlreadyExistsException extends CinemaException {
    public UsernameOrEmailAlreadyExistsException(String message) {
        super(message);
    }

    public UsernameOrEmailAlreadyExistsException() {
        super("Username ou Email já existe");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("O username ou email já está em uso");
        pd.setDetail(this.getMessage());
        return pd;
    }
}
