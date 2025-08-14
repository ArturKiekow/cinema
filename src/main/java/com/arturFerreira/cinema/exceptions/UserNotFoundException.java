package com.arturFerreira.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.UUID;

public class UserNotFoundException extends CinemaException {
    public UserNotFoundException(String username) {
        super("Não existe usuário com o username:" + username);
    }

    public UserNotFoundException(UUID userId) {
        super("Não existe usuário com o id:" + userId);
    }

    @Override
        public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Usuário não encontrado!");
        pd.setDetail(this.getMessage());
        return pd;
    }
}
