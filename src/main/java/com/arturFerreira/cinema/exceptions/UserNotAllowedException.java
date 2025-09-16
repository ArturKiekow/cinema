package com.arturFerreira.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.parameters.P;

public class UserNotAllowedException extends CinemaException {
    public UserNotAllowedException(String message) {
        super(message);
    }

    public UserNotAllowedException() {
        super("Você não tem permissão de realizar esta ação");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        pd.setTitle("Não permitido!");
        pd.setDetail(this.getMessage());
        return pd;
    }


}
