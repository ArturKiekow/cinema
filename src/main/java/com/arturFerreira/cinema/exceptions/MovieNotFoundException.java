package com.arturFerreira.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class MovieNotFoundException extends CinemaException{

  public MovieNotFoundException(String message) {
      super(message);
  }

  public MovieNotFoundException() {
    super("Filme não encontrado!");
  }

  @Override
  public ProblemDetail toProblemDetail() {
      ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
      pd.setTitle("Filme não encontrado!");
      pd.setDetail(this.getMessage());
      return pd;
  }
}
