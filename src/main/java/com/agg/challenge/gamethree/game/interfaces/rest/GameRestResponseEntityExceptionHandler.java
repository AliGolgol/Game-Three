package com.agg.challenge.gamethree.game.interfaces.rest;

import com.agg.challenge.gamethree.game.application.dtos.GameErrorBody;
import com.agg.challenge.gamethree.game.domain.exception.GameRoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GameRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameRoundException.class)
    protected ResponseEntity<GameErrorBody> handleBusinessException(
            GameRoundException ex) {
        GameErrorBody error = GameErrorBody.builder().message(ex.getMessage()).build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
